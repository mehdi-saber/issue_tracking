package com.atrosys.platform.model.da.repository;

import com.atrosys.platform.model.to.Task;
import com.atrosys.platform.model.to.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by asgari on 1/17/18.
 */
public interface CommentRepository  extends JpaRepository<TaskComment,Long>{
    List<TaskComment> findByTask(Task task);
}
