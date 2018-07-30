package com.atrosys.platform.model.to;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "leave_tbl")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name = "username")
    @NotEmpty(message = "*Please provide username")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user")
    @NotEmpty(message = "*Please select username")
    User leaver;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreatedBy() {
        return leaver;
    }

    public void setCreatedBy(User leaver) {
        this.leaver = leaver;
    }
}
