package com.atrosys.platform.controller;

import com.atrosys.platform.model.bl.UserManager;
import com.atrosys.platform.model.service.*;
import com.atrosys.platform.model.to.*;
import com.atrosys.platform.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebMvcProperties;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by asgari on 1/23/18.
 */
@Controller
public class OperatorController {
    @Autowired
    UserManager userManager;
    @Autowired
    TaskService taskService;
    @Autowired
    TagService tagService;
    @Autowired
    CommentService commentService;
    @Autowired
    TaskHistoryService historyService;

    List<Task> pendingTasks;
    Task selectedTask;

    private final StorageService storageService;

    public OperatorController(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping(value="/operator/index", method = RequestMethod.GET)
    public ModelAndView operatorHome(){
        ModelAndView modelAndView = new ModelAndView();
        User user = userManager.getCurrentUser(SecurityContextHolder.getContext());

        List<Task> unassignedTasks = taskService.findAllUnassignedByTags(user.getTags());
        pendingTasks = taskService.findAllPendingTasksByUser(user);
        List<Task> workingTasks = taskService.findAllWorkingTasksByUser(user);
        List<Task> doneTasks = taskService.findAllDoneTasksByUser(user);
        Collections.sort(pendingTasks, new Comparator<Task>() {
            @Override
            public int compare(Task lhs, Task rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return lhs.getPriority() > rhs.getPriority() ? -1 : (lhs.getPriority() < rhs.getPriority()) ? 1 : 0;
            }
        });

        Collections.sort(unassignedTasks, new Comparator<Task>() {
            @Override
            public int compare(Task lhs, Task rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return lhs.getPriority() > rhs.getPriority() ? -1 : (lhs.getPriority() < rhs.getPriority()) ? 1 : 0;
            }
        });


        modelAndView.addObject("pendingTasks",pendingTasks);
        modelAndView.addObject("workingTasks",workingTasks);
        modelAndView.addObject("doneTasks",doneTasks);
        modelAndView.addObject("unassignedTasks",unassignedTasks);
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("operator/index");
        modelAndView.addObject("homeSelected","active");
        return modelAndView;
    }
    @PostMapping(value = "/operator/index/getNewTask")
    public ModelAndView getNewTask(){
        ModelAndView modelAndView = new ModelAndView();
        User user = userManager.getCurrentUser(SecurityContextHolder.getContext());

        List<Task> unassignedTasks = taskService.findAllUnassignedByTags(user.getTags());


        Collections.sort(unassignedTasks, new Comparator<Task>() {
            @Override
            public int compare(Task lhs, Task rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return lhs.getPriority() > rhs.getPriority() ? -1 : (lhs.getPriority() < rhs.getPriority()) ? 1 : 0;
            }
        });

if (LocaleContextHolder.getLocaleContext().getLocale().getDisplayName().equalsIgnoreCase("persian")){
   modelAndView.setViewName("/operator/index :: unassignedFragmentFa");
}else {
    modelAndView.setViewName("/operator/index :: unassignedFragmentEn");
}
        modelAndView.addObject("unassignedTasks",unassignedTasks);


        return modelAndView;
    }
    @PostMapping(value = "/operator/index/sort={id}")
    public ModelAndView getSortedPendingTasks(@PathVariable("id") int id){
        if (id==1){
            Collections.sort(pendingTasks, new Comparator<Task>() {
                @Override
                public int compare(Task lhs, Task rhs) {
                    // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                    return lhs.getCreatedTime().after(rhs.getCreatedTime()) ? -1 : (lhs.getCreatedTime().before(rhs.getCreatedTime())) ? 1 : 0;
                }
            });
        }else if (id==2){
            Collections.sort(pendingTasks, new Comparator<Task>() {
                @Override
                public int compare(Task lhs, Task rhs) {
                    // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                    return lhs.getPriority() > rhs.getPriority() ? -1 : (lhs.getPriority() < rhs.getPriority()) ? 1 : 0;
                }
            });
        }
        ModelAndView view = new ModelAndView();
        view.addObject("pendingTasks",pendingTasks);
        if (LocaleContextHolder.getLocaleContext().getLocale().getDisplayName().equalsIgnoreCase("persian")){
            view.setViewName("/operator/index :: pendingFragmentFa");
        }else {
            view.setViewName("/operator/index :: pendingFragmentEn");
        }
        return view;
    }
    @PostMapping(value = "/operator/index/task={id}")
    public ModelAndView getTaskDetails(@PathVariable("id") int id){
        selectedTask = taskService.findTaskById(id);
        ModelAndView view = new ModelAndView();
        view.addObject("task",selectedTask);
        view.addObject("users",userManager.getAllActiveUsersExceptClients());
        User user = userManager.getCurrentUser(SecurityContextHolder.getContext());
        Tag editTag = tagService.findByTagName("Edit");
        if (editTag!=null && user.getTags().contains(editTag)){
            view.addObject("edit",true);
        }else {
            view.addObject("edit",false);
        }

        view.setViewName("operator/index::taskDetails");
        return view;
    }
    @PostMapping(value = "/operator/index")
    public ModelAndView updateTask(@ModelAttribute Task task){
        if (task.getPriority()!=selectedTask.getPriority()){
            selectedTask.setPriority(task.getPriority());
            TaskHistory history = new TaskHistory();
            history.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            history.setTask(selectedTask);
            history.setStatus(TaskHistory.STATUS_PRIORITY_CHANGED);
            history.setDescription(task.getPriority()+"");
            historyService.save(history);

        }
        List<String> newList = new ArrayList<>();
        for (User user: task.getAssignTos()){
           try {
               newList.add(user.getEmail());
           }catch (Exception e){}
        }
        List<String> oldList = new ArrayList<>();
        for (User user:selectedTask.getAssignTos()){
            try {
                oldList.add(user.getEmail());
            }catch (Exception e){}
        }
       if (!newList.containsAll(oldList) || oldList.size()!=newList.size()){
           selectedTask.setAssignTos(task.getAssignTos());
           TaskHistory history = new TaskHistory();
           history.setCreatedTime(new Timestamp(System.currentTimeMillis()));
           history.setTask(selectedTask);
           history.setStatus(TaskHistory.STATUS_ASSIGN_CHANGED);
           for (User user:task.getAssignTos()){
             try {
                 if (history.getDescription()!=null)
                 history.setDescription(history.getDescription()+"-"+user.getEmail());
                 else
                     history.setDescription(user.getEmail());
             }catch (Exception e){
                 history.setDescription("UNASSIGNED");
             }
           }
           historyService.save(history);
       }

        if (selectedTask.getStatus()!=task.getStatus()){
            selectedTask.setStatus(task.getStatus());
            selectedTask.setStatusChangeTime(new Timestamp(System.currentTimeMillis()));
            TaskHistory history = new TaskHistory();
            history.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            history.setTask(selectedTask);
            history.setStatus(TaskHistory.STATUS_STATUS_CHANGED);
            history.setDescription(task.getStatus()+"");
            historyService.save(history);
        }
        if (task.getContent()!=null){
            User user = userManager.getCurrentUser(SecurityContextHolder.getContext());
            Tag editTag = tagService.findByTagName("Edit");
            if (editTag!=null&& user.getTags().contains(editTag))
                selectedTask.setContent(task.getContent());
        }
        taskService.save(selectedTask);
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/operator/index");
        return view;
    }
    @PostMapping(value = "/operator/index/comment={message}")
    public ModelAndView addComment(@PathVariable("message") String message){
        ModelAndView view = new ModelAndView();
        User user = userManager.getCurrentUser(SecurityContextHolder.getContext());
        TaskComment comment = new TaskComment();
        comment.setCreatedBy(user);
        comment.setMessage(message);
        comment.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        List<TaskComment> comments = selectedTask.getComments();
        comments.add(commentService.save(comment));
        selectedTask.setComments(comments);
        selectedTask = taskService.save(selectedTask);
        view.addObject("task",selectedTask);
        view.setViewName("operator/index::commentsFragment");
        return view;

    }

    @GetMapping("/operator/index/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
