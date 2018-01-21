package com.atrosys.platform.converter;

import com.atrosys.platform.model.service.TaskService;
import com.atrosys.platform.model.service.UserService;
import com.atrosys.platform.model.to.Task;
import com.atrosys.platform.model.to.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by asgari on 1/17/18.
 */
@Component
public class UserListConverter implements Converter<String,List<User>> {
    @Autowired
    UserService userService;

    @Override
    public List<User> convert(String source) {
        String[] ids = source.split(",");
        List<User> users = new ArrayList<>();
        for (String id : ids){
            users.add(userService.findUserById(Integer.parseInt(id)));
        }
        return users;
    }
}
