package com.atrosys.platform.model.bl;

import com.atrosys.platform.Constants;
import com.atrosys.platform.model.service.DaysService;
import com.atrosys.platform.model.service.RoleService;
import com.atrosys.platform.model.service.TaskService;
import com.atrosys.platform.model.service.UserService;
import com.atrosys.platform.model.to.*;
import org.springframework.security.core.context.SecurityContext;
import java.util.ArrayList;
import java.util.List;


public class UserManager {
    private UserService userService;
    private DaysService daysService;
    private RoleService roleService;
    private TaskService taskService;
   public UserManager(UserService userService, DaysService daysService, RoleService roleService,TaskService taskService){
       this.daysService = daysService;
       this.userService = userService;
       this.roleService = roleService;
       this.taskService = taskService;
   }

    public User getCurrentUser(SecurityContext securityContext){

        return userService.findUserByEmail(securityContext.getAuthentication().getName());
    }
    public int getMaximumUserPages(){
        return userService.getUsersCount()/5;
    }
    public List<User> prepareUsersForPaginationByPageNumber(int page){

        if (page==0 || page==1){
            return userService.findAllByLimit(0,5);
        }else {
            int offset = (page-1)*5;
            return userService.findAllByLimit(offset,5);
        }
    }
    public User getUserById(int id){
        return  userService.findUserById(id);
    }
    public List<User> searchUsersByKeyword(String keyword){
      return   userService.searchUserByNameOrFamilyOrEmail(keyword);
    }
    public void modifyUser(User user){
        userService.updateUser(user);
    }
    public List<User> getAllActiveUsersExceptClients(){
        return userService.findActiveUsersExceptClients();
    }
   public boolean isAlreadyExistsEmail(String email){
       return userService.findUserByEmail(email) != null;
   }
   public void registerNewUser(User user){
       userService.saveUser(user);
   }
   public User getOperatorUserForAutoAssignTask(List<Tag> tags){
       List<User> availableUsers = daysService.findAvailableUsers(tags);
       List<User> selectedUsers = new ArrayList<>();
       Role operator = roleService.findByRoleName(Constants.ROLE_OPERATOR);
       for (User user:availableUsers){
           if (user.getRoles().contains(operator)){

               selectedUsers.add(user);
           }


       }
       selectedUsers.sort((lhs, rhs) -> {
           if (taskService.findAllWorkingTasksByUser(lhs).size() < taskService.findAllWorkingTasksByUser(rhs).size()){
               return -1;
           }
           if (taskService.findAllWorkingTasksByUser(lhs).size() > taskService.findAllWorkingTasksByUser(rhs).size()){
               return 1;
           }

           if (taskService.findAllPendingTasksByUser(lhs).size() < taskService.findAllPendingTasksByUser(rhs).size()){
               return -1;
           }
           if (taskService.findAllPendingTasksByUser(lhs).size() > taskService.findAllPendingTasksByUser(rhs).size()){
               return 1;
           }
           return 0;
       });
       //TODO if there is no available user, send it for manager
       return selectedUsers.get(0);
   }

}
