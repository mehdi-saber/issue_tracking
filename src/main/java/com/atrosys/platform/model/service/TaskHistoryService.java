package com.atrosys.platform.model.service;

import com.atrosys.platform.model.to.Task;
import com.atrosys.platform.model.to.TaskHistory;

import java.util.List;

/**
 * Created by asgari on 1/22/18.
 */
public interface TaskHistoryService {
    TaskHistory save(TaskHistory history);
    List<TaskHistory> findByStatusAndTask(int status, Task task);
    List<TaskHistory> findByStatusAndDescriptionAndTask(int status,String desc,Task task);
    TaskHistory findLastChangeStatusByTask(Task task);
}
