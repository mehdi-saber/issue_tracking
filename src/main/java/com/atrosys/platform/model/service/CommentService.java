package com.atrosys.platform.model.service;

import com.atrosys.platform.model.to.Task;
import com.atrosys.platform.model.to.TaskComment;

import java.util.List;

/**
 * Created by asgari on 1/17/18.
 */
public interface CommentService {
    List<TaskComment> findTaskComments(Task task);
    TaskComment save(TaskComment comment);
 }
