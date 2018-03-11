package com.atrosys.platform.model.to;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by asgari on 1/22/18.
 */
@Entity
@Table (name = "task_history_tbl")
public class TaskHistory {
    public static final int STATUS_CREATED = 0;
    public static final int STATUS_STATUS_CHANGED = 1;
    public static final int STATUS_PRIORITY_CHANGED = 2;
    public static final int STATUS_ASSIGN_CHANGED = 3;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name = "description")
    String description;

    @Column(name = "status")
    int status;

    @ManyToOne
    @JoinColumn(name = "fk_task")
    Task task;

    @Column(name = "created_time")
    Timestamp createdTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }
}
