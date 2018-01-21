package com.atrosys.platform.model.to;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "user_tbl")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name = "email")
    @Email(message = "*Please provide a valid Mail")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    @Transient
    private String password;

    @Column(name = "name")
    @NotEmpty(message = "*Please provide your name")
    private String name;

    @Column(name = "last_name")
    @NotEmpty(message = "*Please provide your last name")
    private String lastName;

    @Column(name = "active")
    private boolean active;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "fk_user"), inverseJoinColumns = @JoinColumn(name = "fk_role"))
    private List<Role> roles;

    @ManyToMany
    @JoinTable(name = "user_tag", joinColumns = @JoinColumn(name = "fk_user"), inverseJoinColumns = @JoinColumn(name = "fk_tag"))
    private List<Tag> tags;

    @ManyToMany
    @JoinTable(name = "user_task", joinColumns = @JoinColumn(name = "fk_user"), inverseJoinColumns = @JoinColumn(name = "fk_task"))
    private List<Task> tasks;

    @Column(name = "level")
    private int level;

    @Column(name = "registration_time")
    Timestamp registrationTime;

    @Column(name = "tmp_password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    private String temporaryPassword;

    @Column(name = "tmp_psw_exp_time")
    Timestamp temporaryPasswordExpirationTime;

    @ManyToOne
    @JoinColumn(name = "created_by_user")
    User createdBy;

    @Column(name = "required_change_pass")
    boolean requiredToChangePassword;

    @Column(name = "is_legal")
    boolean legal;

    public boolean isLegal() {
        return legal;
    }

    public void setLegal(boolean legal) {
        this.legal = legal;
    }

    public boolean isRequiredToChangePassword() {
        return requiredToChangePassword;
    }

    public void setRequiredToChangePassword(boolean requiredToChangePassword) {
        this.requiredToChangePassword = requiredToChangePassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Timestamp getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getTemporaryPassword() {
        return temporaryPassword;
    }

    public void setTemporaryPassword(String temporaryPassword) {
        this.temporaryPassword = temporaryPassword;
    }

    public Timestamp getTemporaryPasswordExpirationTime() {
        return temporaryPasswordExpirationTime;
    }

    public void setTemporaryPasswordExpirationTime(Timestamp temporaryPasswordExpirationTime) {
        this.temporaryPasswordExpirationTime = temporaryPasswordExpirationTime;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

}
