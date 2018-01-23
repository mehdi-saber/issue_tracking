package com.atrosys.platform.model.to;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by asgari on 12/27/17.
 */
@Entity
@Table(name = "task_tbl")
public class Task implements Serializable{
    public static final int STATUS_PENDING = 0;
    public static final int STATUS_WORKING = 1;
    public static final int STATUS_DONE = 2;

    public static final int PRIORITY_HIGH = 3;
    public static final int PRIORITY_MEDIUM = 2;
    public static final int PRIORITY_LOW = 1;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name = "title")
    String title;


    @Column(name = "content")
    @NotEmpty(message = "*Please provide task content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "fk_subcategory")
    SubCategory subCategory;

    @ManyToOne
    @JoinColumn(name = "created_by_user")
    User createdBy;

    @Column(name = "created_time")
    Timestamp createdTime;

    @Column(name = "status_change_time")
    Timestamp statusChangeTime;

    @Column(name = "estimated_time")
    Timestamp estimatedTime;

    @Column(name = "status")
    int status;

    @Column(name = "priority")
    int priority;

    @Column(name = "progress_bar")
    int progressBar;

    @Column(name = "attachment")
    String attachment;


    @ManyToMany
    @JoinTable(name = "user_task", joinColumns = @JoinColumn(name = "fk_task"), inverseJoinColumns = @JoinColumn(name = "fk_user"))
    private List<User> assignTos;

    @ManyToMany
    @JoinTable(name = "task_comment", joinColumns = @JoinColumn(name = "fk_task"), inverseJoinColumns = @JoinColumn(name = "fk_comment"))
    private List<TaskComment> comments;

    @OneToMany(mappedBy = "task")
    List<TaskHistory> taskHistories;


    public List<TaskHistory> getTaskHistories() {
        return taskHistories;
    }

    public void setTaskHistories(List<TaskHistory> taskHistories) {
        this.taskHistories = taskHistories;
    }

    public List<TaskComment> getComments() {
        return comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setComments(List<TaskComment> comments) {
        this.comments = comments;
    }

    public List<User> getAssignTos() {
        return assignTos;
    }

    public void setAssignTos(List<User> assignTos) {
        this.assignTos = assignTos;
    }

    public Timestamp getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Timestamp estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getStatusChangeTime() {
        return statusChangeTime;
    }

    public void setStatusChangeTime(Timestamp statusChangeTime) {
        this.statusChangeTime = statusChangeTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(int progressBar) {
        this.progressBar = progressBar;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
