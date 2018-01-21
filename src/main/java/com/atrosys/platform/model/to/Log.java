package com.atrosys.platform.model.to;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by asgari on 12/27/17.
 */
@Entity
@Table(name = "log_tbl")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name = "created_time")
    Timestamp createdTime;

    @Column(name = "type")
    String type;

    @Column(name = "details")
    String details;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
