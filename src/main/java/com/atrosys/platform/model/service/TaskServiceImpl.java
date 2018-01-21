package com.atrosys.platform.model.service;

import com.atrosys.platform.model.da.repository.TaskRepository;
import com.atrosys.platform.model.to.Task;
import com.atrosys.platform.model.to.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by asgari on 1/15/18.
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task findTaskById(int id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> findAllPendingTasksByUser(User user) {
       return taskRepository.findByAssignTosContainsAndStatus(user,0);
    }

    @Override
    public List<Task> findAllWorkingTasksByUser(User user) {
        return taskRepository.findByAssignTosContainsAndStatus(user,1);
    }

    @Override
    public List<Task> findAllDoneTasksByUser(User user) {
        return taskRepository.findByAssignTosContainsAndStatus(user,2);
    }
}
