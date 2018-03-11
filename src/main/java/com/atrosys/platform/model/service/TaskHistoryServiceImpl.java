package com.atrosys.platform.model.service;

import com.atrosys.platform.model.da.repository.TaskHistoryRepository;
import com.atrosys.platform.model.to.Task;
import com.atrosys.platform.model.to.TaskHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by asgari on 1/22/18.
 */
@Service("taskHistoryService")
public class TaskHistoryServiceImpl implements TaskHistoryService{
    @Autowired
    TaskHistoryRepository taskHistoryRepository;

    @Override
    public TaskHistory save(TaskHistory history) {
        return taskHistoryRepository.save(history);
    }

    @Override
    public List<TaskHistory> findByStatusAndTask(int status, Task task) {
        return taskHistoryRepository.findByStatusAndTaskOrderByCreatedTimeDesc(status,task);
    }

    @Override
    public List<TaskHistory> findByStatusAndDescriptionAndTask(int status, String desc, Task task) {
        return taskHistoryRepository.findByStatusAndDescriptionAndTaskOrderByCreatedTimeDesc(status,desc,task);
    }

    @Override
    public TaskHistory findLastChangeStatusByTask(Task task) {
        TaskHistory taskHistory;
        List<TaskHistory> histories =  taskHistoryRepository.findByStatusAndAndTaskOrderByIdDesc(TaskHistory.STATUS_STATUS_CHANGED,task);
        if (histories!=null && histories.size()>0) {
            taskHistory = histories.get(0);
        }else {

            taskHistory = taskHistoryRepository.findByStatusAndAndTaskOrderByIdDesc(TaskHistory.STATUS_CREATED,task).get(0);
        }
        return taskHistory;
    }
}
