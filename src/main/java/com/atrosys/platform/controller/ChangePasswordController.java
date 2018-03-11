package com.atrosys.platform.controller;

import com.atrosys.platform.model.bl.UserManager;
import com.atrosys.platform.model.service.UserService;
import com.atrosys.platform.model.to.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by Kasra on 3/4/2018.
 */
@Controller
public class ChangePasswordController {
    @Autowired
    private UserService userService;
    @Autowired
    UserManager userManager;

    @GetMapping("change/password")
    public ModelAndView changePassword(){
        ModelAndView view = new ModelAndView();
        view.addObject("user",userManager.getCurrentUser(SecurityContextHolder.getContext()) );
        return view;
    }
    @PostMapping("change/password")
    public String confirmChangePassword(@Valid User user, BindingResult result){
        User updateUser = userManager.getCurrentUser(SecurityContextHolder.getContext());
        updateUser.setPassword(user.getPassword());
        updateUser.setRequiredToChangePassword(false);
        userService.saveUser(updateUser);
        SecurityContextHolder.clearContext();
        return "/login";
    }
}
