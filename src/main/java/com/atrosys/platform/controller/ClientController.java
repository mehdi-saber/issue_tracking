package com.atrosys.platform.controller;

import com.atrosys.platform.TestJob;
import com.atrosys.platform.model.bl.UserManager;
import com.atrosys.platform.model.service.*;
import com.atrosys.platform.model.to.Task;
import com.atrosys.platform.model.to.TaskHistory;
import com.atrosys.platform.model.to.User;
import com.atrosys.platform.storage.StorageService;
import com.atrosys.platform.util.DateUtil;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by asgari on 1/22/18.
 */
@Controller
public class ClientController {

    @Autowired
    TaskService taskService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    SubCategoryService subCategoryService;
    @Autowired
    TaskHistoryService taskHistoryService;
    @Autowired
    UserManager userManager;
    @Autowired
    Scheduler scheduler;
    private final StorageService storageService;

    @Autowired
    final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ClientController(StorageService storageService, SimpMessagingTemplate messagingTemplate) {
        this.storageService = storageService;
        this.messagingTemplate = messagingTemplate;
    }


    @RequestMapping(value="/client/index", method = RequestMethod.GET)
    public ModelAndView clientHome(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user =userManager.getCurrentUser(SecurityContextHolder.getContext());
        ModelAndView view = new ModelAndView();
        view.addObject("task",new Task());
        view.setViewName("client/index");
        view.addObject("categories",categoryService.findAllCategories());
        List<Task> tasks = taskService.findAllByCreator(user);
        List<Task> pendingTasks = new ArrayList<>();
        List<Task> workingTasks = new ArrayList<>();
        List<Task> doneTasks = new ArrayList<>();
        for (Task task:tasks){
            TaskHistory taskHistory = taskHistoryService.findLastChangeStatusByTask(task);
            List<TaskHistory> histories = new ArrayList<>();
            if (taskHistory.getDescription().equals("0")){
                histories.add(taskHistory);
                task.setTaskHistories(histories);
                pendingTasks.add(task);
            }
            histories = taskHistoryService.findByStatusAndDescriptionAndTask(TaskHistory.STATUS_STATUS_CHANGED,"1",task);
            if (taskHistory.getDescription().equals("1")){
                histories.add(taskHistory);
                task.setTaskHistories(histories);
                workingTasks.add(task);
            }
            histories = taskHistoryService.findByStatusAndDescriptionAndTask(TaskHistory.STATUS_STATUS_CHANGED,"2",task);
            if (taskHistory.getDescription().equals("2")){
                histories.add(taskHistory);
                task.setTaskHistories(histories);
                doneTasks.add(task);
            }

        }
        Collections.sort(pendingTasks, new Comparator<Task>() {
            @Override
            public int compare(Task lhs, Task rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return lhs.getCreatedTime().after(rhs.getCreatedTime()) ? -1 : (lhs.getCreatedTime().before(rhs.getCreatedTime())) ? 1 : 0;
            }
        });
        Collections.sort(workingTasks, new Comparator<Task>() {
            @Override
            public int compare(Task lhs, Task rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return lhs.getCreatedTime().after(rhs.getCreatedTime()) ? -1 : (lhs.getCreatedTime().before(rhs.getCreatedTime())) ? 1 : 0;
            }
        });
        Collections.sort(doneTasks, new Comparator<Task>() {
            @Override
            public int compare(Task lhs, Task rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return lhs.getCreatedTime().after(rhs.getCreatedTime()) ? -1 : (lhs.getCreatedTime().before(rhs.getCreatedTime())) ? 1 : 0;
            }
        });

          if (pendingTasks.size()!=0) view.addObject("pendingTasks",pendingTasks);
        if (workingTasks.size()!=0) view.addObject("workingTasks",workingTasks);
       if (doneTasks.size()!=0)view.addObject("doneTasks",doneTasks);


        return view;
    }
    @PostMapping(value = "client/index/category={id}")
    public ModelAndView getSubcategories(@PathVariable("id")int id){
        ModelAndView view = new ModelAndView();
        view.addObject("subcategories",subCategoryService.findSubCategoriesByParent(categoryService.findCategoryById(id)));
        view.addObject("task",new Task());
        view.setViewName("client/index::subcategories");
        return view;
    }
    @PostMapping("/client/index")
    public ModelAndView addTask(@RequestParam("file") MultipartFile file,@ModelAttribute Task task) {


ModelAndView view = new ModelAndView();

        User user = userManager.getCurrentUser(SecurityContextHolder.getContext());

        task.setPriority(task.getSubCategory().getPriority());

        task.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        task.setCreatedBy(user);
        User assignTo = userManager.getOperatorUserForAutoAssignTask(task.getSubCategory().getTags());

        List<User> assignTos = new ArrayList<>();
        assignTos.add(assignTo);
        task.setAssignTos(assignTos);
        task = taskService.save(task);

        if (!file.isEmpty()) {
String name = file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."));
String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),file.getOriginalFilename().length());


String fileName = name+task.getId()+type;

            storageService.store(file,fileName);
                task.setAttachment(fileName);
                taskService.save(task);



        }
        TaskHistory history = new TaskHistory();
        history.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        history.setStatus(TaskHistory.STATUS_CREATED);
        history.setDescription("0");
        history.setTask(task);
        taskHistoryService.save(history);
        view.addObject("task",new Task());
        view.setViewName("redirect:/client/index");
        messagingTemplate.convertAndSend("/topic/greetings", 1);

JobDataMap map =  new JobDataMap();
map.put("taskService",taskService);
        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(TestJob.class)
                .withIdentity("Job"+task.getId(), "group"+task.getId())
                .usingJobData("id", task.getId())
                .usingJobData(map)
                .build();

        // Trigger the job to run now, and then every 40 seconds
        Trigger trigger = newTrigger()
                .withIdentity("Trigger"+task.getId(), "group"+task.getId())
                .startAt(DateUtil.moveToDateFromCurrentDate(0,0,1).getTime())

                .build();
        // Tell quartz to schedule the job using our trigger
        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }


        return view;
    }
}
