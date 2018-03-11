package com.atrosys.platform.configuration;

import com.atrosys.platform.model.service.DaysService;
import com.atrosys.platform.model.service.DaysServiceImpl;
import com.atrosys.platform.model.to.Task;
import com.atrosys.platform.model.to.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;

/**
 * Created by Kasra on 2/13/2018.
 */
@Component
public class Stopwatch {


    @Autowired
    DaysService daysService;

static Map <Integer,Integer> timerTask = new HashMap<>();

    private   int setInterval(int secs, Timer timer) {
        if (secs == 1)
            timer.cancel();
        return --secs;
    }
    public void testForCountDownTasks(){

            List<User> availableUsers = daysService.findAvailableUsers(null);
            for (User user: availableUsers){
                List<Task> tasks = user.getTasks();
                for (Task task:tasks){

                    int day = task.getSubCategory().getAllowedPendingDays();
                    int hour = task.getSubCategory().getAllowedPendingHours();
                    int mins = task.getSubCategory().getAllowedPendingMinutes();
                    int secs = (day * 24 * 60 * 60)+(hour * 60 * 60) + (mins * 60);
                    timerTask.put(task.getId(),secs);
                    int delay = 1000;
                    int period = 1000;
                    Timer  timer = new Timer();
                    timer.scheduleAtFixedRate(new TimerTask() {

                        public void run() {

                            int a  = setInterval(timerTask.get(task.getId()),timer);
                            System.out.println(a);
                            timerTask.replace(task.getId(),a);

                        }
                    }, delay, period);

                }
            }

    }
}