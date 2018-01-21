package com.atrosys.platform.model.to;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by asgari on 1/2/18.
 */
@Entity
@Table(name = "task_comment_tbl")
public class TaskComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name = "msg")
    String message;

    @ManyToOne
    @JoinColumn(name = "created_by_user")
    User createdBy;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
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
