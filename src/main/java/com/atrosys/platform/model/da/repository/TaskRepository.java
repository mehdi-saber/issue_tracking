package com.atrosys.platform.model.da.repository;

import com.atrosys.platform.model.to.Task;
import com.atrosys.platform.model.to.User;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by asgari on 12/27/17.
 */
public interface TaskRepository extends JpaRepository<Task,Long> {
    Task findById(int id);

    List <Task> findByAssignTosContainsAndStatus(User user,int status);


}
