package com.atrosys.platform.model.to;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Kasra on 1/27/2018.
 */
@Entity
@Table(name = "days_tbl")
public class Days {
    public static final String SU = "Su";
    public static final String MO = "Mo";
    public static final String TU = "Tu";
    public static final String WE = "We";
    public static final String TH = "Th";
    public static final String FR = "Fr";
    public static final String SA = "Sa";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name = "day")
    String day;

    @Column(name = "start_hour")
    int startHour;

    @Column(name = "end_hour")
    int endHour;


    @ManyToOne
    @JoinColumn(name = "fk_user")
    User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }
}
