package com.atrosys.platform.controller;


import com.atrosys.platform.model.bl.UserManager;
import com.atrosys.platform.model.service.*;
import com.atrosys.platform.model.to.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.*;


@Controller
public class AdminController {

    private final RoleService roleService;
    private final TagService tagService;
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;
    private final SmsLevelService smsLevelService;
    private final EmailLevelService emailLevelService;
    private final LeaveService leaveService;

    private final CommentService commentService;

    private User selectedUser;
    private Category selectedCategory;
    private List<Task> pendingTasks;
    private Task selectedTask;

    private final UserManager userManager;
    private final TaskService taskService;

    @Autowired
    public AdminController(TaskService taskService, UserManager userManager, CommentService commentService, SubCategoryService subCategoryService, RoleService roleService, TagService tagService, CategoryService categoryService, SmsLevelService smsLevelService, EmailLevelService emailLevelService ,LeaveService leaveService) {
        this.taskService = taskService;
        this.userManager = userManager;
        this.commentService = commentService;
        this.subCategoryService = subCategoryService;
        this.roleService = roleService;
        this.tagService = tagService;
        this.categoryService = categoryService;
        this.smsLevelService = smsLevelService;
        this.emailLevelService = emailLevelService;
        this.leaveService = leaveService;
    }


    @RequestMapping(value = "admin/createAccount", method = RequestMethod.GET)
    public ModelAndView createAccount(){
ModelAndView view = new ModelAndView();
view.addObject("accountSelected","active");
        User user = new User();
        List<Role> roles = roleService.findAllRoles();

        view.addObject("user",user);
        view.addObject("roles",roles);


        return view;
    }

    @RequestMapping(value = "admin/accounts")
    public ModelAndView accounts(){
        ModelAndView view = new ModelAndView();
        view.addObject("users",userManager.prepareUsersForPaginationByPageNumber(0));
        view.addObject("accountSelected","active");
        view.addObject("pageNumbers",userManager.getMaximumUserPages());


        return view;
    }
    @RequestMapping(value = "admin/accounts/page={page}")
    public ModelAndView accounts( @PathVariable("page") int page){
        ModelAndView view = new ModelAndView();
        view.addObject("users",userManager.prepareUsersForPaginationByPageNumber(page));
        view.setViewName("admin/accounts :: resultsList");
        return view;
    }
    @GetMapping(value = "admin/accounts/search={search}")
    public ModelAndView accounts( @PathVariable("search") String search){
        search = search.replace("+",".");
        ModelAndView view = new ModelAndView();
        view.addObject("users",userManager.searchUsersByKeyword(search));
        view.setViewName("admin/accounts :: resultsList");
        return view;
    }
    @RequestMapping(value = "admin/accounts/user={id}")
    public ModelAndView getSelectedUser( @PathVariable("id") int  id){
        ModelAndView view = new ModelAndView();
        view.addObject("roles",roleService.findAllRoles());
        selectedUser = userManager.getUserById(id);
        view.addObject("selectedUser",selectedUser);
        view.addObject("tags",tagService.findAllTags());
        view.setViewName("admin/accounts :: details");
        return view;
    }
    @PostMapping(value = "admin/accounts")
    public ModelAndView updateUser(@ModelAttribute User user){
           selectedUser.setRoles(user.getRoles());
           selectedUser.setActive(user.isActive());
           selectedUser.setRequiredToChangePassword(user.isRequiredToChangePassword());
           selectedUser.setLegal(user.isLegal());
           selectedUser.setTags(user.getTags());
        ModelAndView view = new ModelAndView();
            userManager.modifyUser(selectedUser);


            view.setViewName("redirect:accounts");


        return view;
    }

    private List<Category> categoryPagination(int page){

        if (page==0 || page==1){
            return categoryService.findAllByLimit(0,5);
        }else {
            int offset = (page-1)*5;
            return categoryService.findAllByLimit(offset,5);
        }
    }
    @RequestMapping(value = "admin/tags")
    public ModelAndView tags(){
        ModelAndView view = new ModelAndView();
        view.addObject("settingSelected","active");
        view.addObject("tag",new Tag());
        return view;
    }
    @RequestMapping(value = "admin/leave")
    public ModelAndView leaves(){
        ModelAndView view = new ModelAndView();
        view.addObject("settingSelected","active");
        view.addObject("leave",new Leave());
        return view;
    }
    @PostMapping(value = "admin/tags")
    public ModelAndView saveTag(@ModelAttribute Tag tag){
        if (tagService.findByTagName(tag.getName())==null){
            tag.setCreatedBy(userManager.getCurrentUser(SecurityContextHolder.getContext()));
            tagService.save(tag);
        }
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:tags");
        return view;
    }

    @RequestMapping(value = "admin/categories")
    public ModelAndView categories(){
        ModelAndView view = new ModelAndView();
        view.addObject("settingSelected","active");
        view.addObject("category",new Category());
        view.addObject("pageNumbers",categoryService.getCategoriesCount()/5);
        view.addObject("categories",categoryPagination(0));
        return view;
    }
    @PostMapping(value = "admin/categories")
    public ModelAndView saveCategory(@ModelAttribute Category category){
        ModelAndView view = new ModelAndView();
        if (categoryService.findCategoryByTitle(category.getTitle())==null){
            category.setCreatedBy(userManager.getCurrentUser(SecurityContextHolder.getContext()));
            categoryService.save(category);
        }
        view.setViewName("redirect:categories");
        return view;
    }
    @RequestMapping(value = "admin/categories/page={page}")
    public ModelAndView categories( @PathVariable("page") int page){
        ModelAndView view = new ModelAndView();

        view.addObject("categories",categoryPagination(page));
        view.setViewName("admin/categories :: categoryList");
        return view;
    }

    @RequestMapping(value = "admin/categories/search={search}")
    public ModelAndView searchCategories( @PathVariable("search") String search){
        ModelAndView view = new ModelAndView();
        //model.addAttribute("resultList", pagination(page));
        List<Category> categories = categoryService.searchCategoryByTitle(search);
        view.addObject("categories",categories);
        view.setViewName("admin/categories :: categoryList");
        return view;
    }
    @RequestMapping(value = "admin/categories/category={id}")
    public ModelAndView getSelectedCategory( @PathVariable("id") int  id){
        ModelAndView view = new ModelAndView();
        selectedCategory = categoryService.findCategoryById(id);
        view.addObject("selectedCategory",selectedCategory);
        view.addObject("tags",tagService.findAllTags());
        view.addObject("subCategories",subCategoryService.findSubCategoriesByParent(selectedCategory));
        view.addObject("subCategory",new SubCategory());
        view.setViewName("admin/categories :: details");
        return view;
    }
    @PostMapping(value = "admin/categories/sub")
    public ModelAndView saveSubCategory(@Valid SubCategory subCategory){
        if (subCategoryService.findSubCategoryByTitle(subCategory.getTitle())==null){
            subCategory.setCreatedBy(userManager.getCurrentUser(SecurityContextHolder.getContext()));
            subCategory.setCategory(selectedCategory);
            subCategoryService.save(subCategory);
        }
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/admin/categories");
        return view;
    }
    @RequestMapping(value = "admin/configuration")
    public ModelAndView configuration(){
        ModelAndView view = new ModelAndView();
        view.addObject("settingSelected","active");
        return view;
    }
    @RequestMapping(value = "admin/smsLevel")
    public ModelAndView smsLevel(){
        ModelAndView view = new ModelAndView();
        view.addObject("settingSelected","active");
        view.addObject("smsLevel",new SmsLevel());
        view.addObject("pageNumbers",smsLevelService.getSmsLevelsCount()/5);
        view.addObject("smsLevels",smsLevelService.findAllSmsLevels());
        view.addObject("roles",roleService.findAllRoles());
        return view;
    }
    @RequestMapping(value = "admin/emailLevel")
    public ModelAndView emailLevel(){
        ModelAndView view = new ModelAndView();
        view.addObject("settingSelected","active");
        view.addObject("emailLevel",new EmailLevel());
        view.addObject("pageNumbers",emailLevelService.getEmailLevelsCount()/5);
        view.addObject("emailLevels",emailLevelService.findAllEmailLevels());
        view.addObject("roles",roleService.findAllRoles());
        return view;
    }
    @RequestMapping(value = "admin/tasks")
    public ModelAndView tasks(){
        ModelAndView view = new ModelAndView();
        view.addObject("tasksSelected","active");
        view.addObject("task",new Task());
        view.addObject("categories",categoryService.findAllCategories());
        view.addObject("users",userManager.getAllActiveUsersExceptClients());
        view.addObject("selectedUsers",new ArrayList<User>());
        return view;
    }
    @PostMapping(value = "admin/tasks/category={id}")
    public ModelAndView getSubcategories(@PathVariable("id")int id){
        ModelAndView view = new ModelAndView();
        view.addObject("subcategories",subCategoryService.findSubCategoriesByParent(categoryService.findCategoryById(id)));
        view.addObject("task",new Task());
        view.setViewName("admin/tasks::subcategories");
        return view;
    }
    @PostMapping(value = "admin/tasks")
    public ModelAndView createTask(@ModelAttribute Task task, HttpServletRequest request){
        task.setCreatedBy(userManager.getCurrentUser(SecurityContextHolder.getContext()));
        task.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        String s = request.getParameter("assignTo");
        String[]ids = s.split(",");


        List<User> assignTos = new ArrayList<>();
       for (String id:ids){
           User user = userManager.getUserById(Integer.parseInt(id));
         assignTos.add(user);


       }
        task.setAssignTos(assignTos);
        taskService.save(task);
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:tasks");
        return view;
    }
    @RequestMapping(value = "admin/errorLog")
    public ModelAndView errorLog(){
        ModelAndView view = new ModelAndView();
        view.addObject("reportSelected","active");
        return view;
    }
    @RequestMapping(value = "admin/createAccount", method = RequestMethod.POST)
    public ModelAndView saveUser(@Valid User user, BindingResult result){
        ModelAndView view = new ModelAndView();


        if (userManager.isAlreadyExistsEmail(user.getEmail())) {
            result
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (result.hasErrors()) {
            view.addObject("roles",roleService.findAllRoles());
            view.setViewName("admin/createAccount");
        } else {

            user.setCreatedBy(userManager.getCurrentUser(SecurityContextHolder.getContext()));
            user.setRegistrationTime(new Timestamp(System.currentTimeMillis()));
            userManager.registerNewUser(user);
            view.addObject("successMessage", "User has been registered successfully");
            view.addObject("user", new User());
            view.addObject("roles",roleService.findAllRoles());
            view.setViewName("admin/createAccount");

        }
        return view;
    }
    @RequestMapping(value = "admin/smsLevel", method = RequestMethod.POST)
    public ModelAndView saveSmsLevel(@ModelAttribute SmsLevel smsLevel){
        ModelAndView view = new ModelAndView();
        smsLevelService.save(smsLevel);
            view.addObject("successMessage", "Sms Level has been Added successfully");
            view.addObject("smsLevel", new SmsLevel());
            view.addObject("roles",roleService.findAllRoles());
            view.addObject("smsLevels",smsLevelService.findAllSmsLevels());
            view.setViewName("redirect:smsLevel");

        return view;
    }
    @RequestMapping(value = "admin/emailLevel", method = RequestMethod.POST)
    public ModelAndView saveEmailLevel(@ModelAttribute EmailLevel emailLevel){
        ModelAndView view = new ModelAndView();
        emailLevelService.save(emailLevel);
        view.addObject("successMessage", "Email Level has been Added successfully");
        view.addObject("emailLevel", new EmailLevel());
        view.addObject("roles",roleService.findAllRoles());
        view.addObject("emailLevels",emailLevelService.findAllEmailLevels());
        view.setViewName("redirect:emailLevel");

        return view;
    }
    @RequestMapping(value="/admin/index", method = RequestMethod.GET)
    public ModelAndView adminHome(){

        ModelAndView modelAndView = new ModelAndView();
        User user = userManager.getCurrentUser(SecurityContextHolder.getContext());
        pendingTasks = new ArrayList<>();
        pendingTasks = taskService.findAllPendingTasksByUser(user);
        List<Task> workingTasks = taskService.findAllWorkingTasksByUser(user);
        List<Task> doneTasks = taskService.findAllDoneTasksByUser(user);
        pendingTasks.sort((lhs, rhs) -> {
            // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
            return lhs.getPriority() > rhs.getPriority() ? -1 : (lhs.getPriority() < rhs.getPriority()) ? 1 : 0;
        });


        modelAndView.addObject("pendingTasks",pendingTasks);
        modelAndView.addObject("workingTasks",workingTasks);
        modelAndView.addObject("doneTasks",doneTasks);

        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/index");
        modelAndView.addObject("homeSelected","active");
        return modelAndView;
    }
    @PostMapping(value = "/admin/index/sort={id}")
    public ModelAndView getSortedPendingTasks(@PathVariable("id") int id){
        if (id==1){
            pendingTasks.sort((lhs, rhs) -> {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return lhs.getCreatedTime().after(rhs.getCreatedTime()) ? -1 : (lhs.getCreatedTime().before(rhs.getCreatedTime())) ? 1 : 0;
            });
        }else if (id==2){
            pendingTasks.sort((lhs, rhs) -> {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return lhs.getPriority() > rhs.getPriority() ? -1 : (lhs.getPriority() < rhs.getPriority()) ? 1 : 0;
            });
        }
        ModelAndView view = new ModelAndView();
        view.addObject("pendingTasks",pendingTasks);
        view.setViewName("admin/index::pendingSection");
        return view;
    }
    @PostMapping(value = "/admin/index/task={id}")
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

        view.setViewName("admin/index::taskDetails");
        return view;
    }
    @PostMapping(value = "/admin/index")
    public ModelAndView updateTask(@ModelAttribute Task task){

        selectedTask.setPriority(task.getPriority());
        selectedTask.setAssignTos(task.getAssignTos());
        if (selectedTask.getStatus()!=task.getStatus()){
            selectedTask.setStatus(task.getStatus());
            selectedTask.setStatusChangeTime(new Timestamp(System.currentTimeMillis()));
        }
        if (task.getContent()!=null){
            User user = userManager.getCurrentUser(SecurityContextHolder.getContext());
            Tag editTag = tagService.findByTagName("Edit");
            if (editTag!=null&& user.getTags().contains(editTag))
            selectedTask.setContent(task.getContent());
        }
        taskService.save(selectedTask);
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/admin/index");
return view;
    }
    @PostMapping(value = "/admin/index/comment={message}")
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
        view.setViewName("admin/index::commentsFragment");
        return view;

    }

}
