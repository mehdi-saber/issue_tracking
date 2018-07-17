package com.atrosys.platform.model.to;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by asgari on 12/27/17.
 */
@Entity
@Table(name = "category_tbl")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "*Please provide category title")
    private String title;

    @Column(name = "en_title")
    @NotEmpty(message = "*Please provide category english title")
    private String enTitle;

    @Column(name = "code")
    @NotEmpty(message = "*Please provide code")
    private String code;

    @Column(name = "internal")
    private boolean internal;

    @ManyToOne
    @JoinColumn(name = "created_by_user")
    User createdBy;

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

    public String getEnTitle() {
        return enTitle;
    }

    public void setEnTitle(String enTitle) {
        this.enTitle = enTitle;
    }

    public boolean isInternal() {
        return internal;
    }

    public void setInternal(boolean internal) {
        this.internal = internal;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
