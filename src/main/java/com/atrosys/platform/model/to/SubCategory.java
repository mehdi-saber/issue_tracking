package com.atrosys.platform.model.to;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by asgari on 12/27/17.
 */
@Entity
@Table(name = "subcategory_tbl")
public class SubCategory {
    public static final int PRIORITY_HIGH = 3;
    public static final int PRIORITY_MEDIUM = 2;
    public static final int PRIORITY_LOW = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "*Please provide subcategory title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "fk_category")
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "subcategory_tag", joinColumns = @JoinColumn(name = "fk_subcategory"), inverseJoinColumns = @JoinColumn(name = "fk_tag"))
    private List<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "created_by_user")
    User createdBy;

    @Column(name = "priority")
    int priority;

    @Column(name = "allowed_pending_minutes",columnDefinition = "int(6) default 0")
    int allowedPendingMinutes;

    @Column(name = "allowed_pending_hours",columnDefinition = "int(6) default 0")
    int allowedPendingHours;

    @Column(name = "allowed_pending_days",columnDefinition = "int(6) default 0")
    int allowedPendingDays;

    @Column(name = "allowed_working_minutes",columnDefinition = "int(6) default 0")
    int allowedWorkingMinutes;

    @Column(name = "allowed_working_hours",columnDefinition = "int(6) default 0")
    int allowedWorkingHours;

    @Column(name = "allowed_working_days",columnDefinition = "int(6) default 0")
    int allowedWorkingDays;

    @Column(name = "done_point",columnDefinition = "int(6) default 0")
    int donePoint;
    @Column(name = "reject_point",columnDefinition = "int(6) default 0")
    int rejectPoint;
    @Column(name = "too_late_point",columnDefinition = "int(6) default 0")
    int tooLatePoint;

    @Column(name = "bonus_point",columnDefinition = "int(6) default 0")
    int bonusPoint;

    public int getBonusPoint() {
        return bonusPoint;
    }

    public void setBonusPoint(int bonusPoint) {
        this.bonusPoint = bonusPoint;
    }

    public int getAllowedPendingMinutes() {
        return allowedPendingMinutes;
    }

    public void setAllowedPendingMinutes(int allowedPendingMinutes) {
        this.allowedPendingMinutes = allowedPendingMinutes;
    }

    public int getAllowedPendingHours() {
        return allowedPendingHours;
    }

    public void setAllowedPendingHours(int allowedPendingHours) {
        this.allowedPendingHours = allowedPendingHours;
    }

    public int getAllowedPendingDays() {
        return allowedPendingDays;
    }

    public void setAllowedPendingDays(int allowedPendingDays) {
        this.allowedPendingDays = allowedPendingDays;
    }

    public int getAllowedWorkingMinutes() {
        return allowedWorkingMinutes;
    }

    public void setAllowedWorkingMinutes(int allowedWorkingMinutes) {
        this.allowedWorkingMinutes = allowedWorkingMinutes;
    }

    public int getAllowedWorkingHours() {
        return allowedWorkingHours;
    }

    public void setAllowedWorkingHours(int allowedWorkingHours) {
        this.allowedWorkingHours = allowedWorkingHours;
    }

    public int getAllowedWorkingDays() {
        return allowedWorkingDays;
    }

    public void setAllowedWorkingDays(int allowedWorkingDays) {
        this.allowedWorkingDays = allowedWorkingDays;
    }

    public int getDonePoint() {
        return donePoint;
    }

    public void setDonePoint(int donePoint) {
        this.donePoint = donePoint;
    }

    public int getRejectPoint() {
        return rejectPoint;
    }

    public void setRejectPoint(int rejectPoint) {
        this.rejectPoint = rejectPoint;
    }

    public int getTooLatePoint() {
        return tooLatePoint;
    }

    public void setTooLatePoint(int tooLatePoint) {
        this.tooLatePoint = tooLatePoint;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
