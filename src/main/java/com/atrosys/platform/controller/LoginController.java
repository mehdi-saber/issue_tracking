package com.atrosys.platform.controller;

import javax.validation.Valid;

import com.atrosys.platform.Constants;
import com.atrosys.platform.model.service.RoleService;
import com.atrosys.platform.model.to.Role;
import com.atrosys.platform.model.to.User;
import com.atrosys.platform.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
//        System.out.println(new BCryptPasswordEncoder().encode("1234"));
        return modelAndView;
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {

            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }


    @RequestMapping(value = "/something")
    public ModelAndView something() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", "Choose your destiny!!!").setViewName("something");
        return modelAndView;
    }

    @RequestMapping(value = "/access-denied")
    public ModelAndView accessDenied() {

        return new ModelAndView();
    }

    //todo: get json as encrypt
    @RequestMapping(value = "/createAccountJson", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public void saveUserJson(@RequestBody User user, BindingResult bindingResult) {
        //ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        } else {
            List<Role> roles = new ArrayList<>();
            roles.add(roleService.findByRoleName(Constants.ROLE_CLIENT));
            user.setRoles(roles);
            userService.saveUser(user);
        }

    }


}