package com.atrosys.platform;

import com.atrosys.platform.model.service.TaskService;
import com.atrosys.platform.model.service.TaskServiceImpl;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Kasra on 3/15/2018.
 */

public class TestJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println(new Date());
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        TaskService taskService = (TaskService) dataMap.get("taskService");
        int id = dataMap.getInt("id");
        System.out.println(taskService.findTaskById(id).getTitle());
    }
}
