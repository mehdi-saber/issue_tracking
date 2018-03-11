package com.atrosys.platform.model.to;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Kasra on 2/13/2018.
 */
@Entity
@Table(name = "score_tbl")
public class Score {
    public static final String DESC_DONE = "DONE_POINT";
    public static final String DESC_REJECTED = "REJECTED_POINT";
    public static final String DESC_TOO_LATE = "TOO_LATE_POINT";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column (name = "point",columnDefinition = "int (6) default 0 ")
    int point;
    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User forUser;
    @ManyToOne
    @JoinColumn(name = "fk_task")
    private Task task;
    @ManyToOne
    @JoinColumn(name = "fk_subcategory")
    private SubCategory subCategory;

    @Column(name = "description")
    private String description;

    @Column (name = "timestamp")
    Timestamp timestamp;

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public User getForUser() {
        return forUser;
    }

    public void setForUser(User forUser) {
        this.forUser = forUser;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }
}
