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

    @Column(name = "priority",columnDefinition = "default 1")
    int priority;

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
