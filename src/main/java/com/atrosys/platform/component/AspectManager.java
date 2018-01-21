package com.atrosys.platform.component;

import com.atrosys.platform.model.service.EmailService;
import com.atrosys.platform.model.to.Email;
import com.atrosys.platform.model.to.User;
import com.atrosys.platform.util.EmailTemplate;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by asgari on 12/26/17.
 */
@Aspect
@Component
public class AspectManager {
    @Autowired
    EmailService emailService;

 //   @After("execution(* com.atrosys.platform.model.service.UserService.saveUser (com.atrosys.platform.model.to.User)) && args(user))")
    public void afterRegistration(User user) throws Exception{
        Email mail = new Email();
        mail.setFrom("AtroSys");
        List<String> tos = new ArrayList<>();
        tos.add("k.asgari@live.com");
     //   tos.add("k.asgari@hotmail.com");
        mail.setTo(tos);
        mail.setSubject("Welcome!!!");
        mail.setHtml(true);
        Map<String,String> replace = new HashMap<>();
        replace.put("user.name",user.getName());
        replace.put("user.lastName",user.getLastName());
        replace.put("user.email",user.getEmail());
        replace.put("time",new Date(System.currentTimeMillis()).toString());
        EmailTemplate template = new EmailTemplate("mail.html");
        mail.setMessage(template.getTemplate(replace));
        mail.setAttachment("test.txt");
        emailService.send(mail);


    }

}

