package com.atrosys.platform.model.service;

import com.atrosys.platform.model.da.repository.CommentRepository;
import com.atrosys.platform.model.to.Task;
import com.atrosys.platform.model.to.TaskComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by asgari on 1/17/18.
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentRepository commentRepository;
    @Override
    public List<TaskComment> findTaskComments(Task task) {
        return commentRepository.findByTask(task);
    }

    @Override
    public TaskComment save(TaskComment comment) {
        return commentRepository.save(comment);
    }
}
