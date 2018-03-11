package com.atrosys.platform.model.da.repository;

import com.atrosys.platform.model.to.Task;
import com.atrosys.platform.model.to.TaskHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by asgari on 1/22/18.
 */
public interface TaskHistoryRepository extends JpaRepository<TaskHistory,Long> {
    List<TaskHistory> findByStatusAndTaskOrderByCreatedTimeDesc(int status, Task task);
    List<TaskHistory> findByStatusAndDescriptionAndTaskOrderByCreatedTimeDesc(int status,String description,Task task);
    List<TaskHistory> findByStatusAndAndTaskOrderByIdDesc(int status,Task task);
}
