package com.atrosys.platform.controller;

/**
 * Created by Kasra on 1/27/2018.
 */
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

    @MessageMapping("/newTask")
    @SendTo("/topic/greetings")
    public int notifyTask (String staus) throws Exception {

        return 1;
    }

}