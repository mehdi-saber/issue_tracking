package com.atrosys.platform.model.service;

import com.atrosys.platform.model.to.Task;
import com.atrosys.platform.model.to.User;

import java.util.List;

/**
 * Created by asgari on 12/27/17.
 */
public interface TaskService {
    Task save(Task task);
    Task findTaskById(int id);
    List<Task> findAllPendingTasksByUser(User user);
    List<Task> findAllWorkingTasksByUser(User user);
    List<Task> findAllDoneTasksByUser(User user);

}
