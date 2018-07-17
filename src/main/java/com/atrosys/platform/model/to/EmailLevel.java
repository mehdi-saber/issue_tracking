package com.atrosys.platform.model.to;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "email_level_tbl")
public class EmailLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "*Please provide email title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "created_by_user")
    User createdBy;

    @ManyToMany
    @JoinTable(name = "email_role", joinColumns = @JoinColumn(name = "fk_email"), inverseJoinColumns = @JoinColumn(name = "fk_role"))
    private List<Role> roles;

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
