package com.atrosys.platform.model.service;

import com.atrosys.platform.Constants;
import com.atrosys.platform.model.da.repository.DaysRepository;
import com.atrosys.platform.model.to.Days;
import com.atrosys.platform.model.to.Role;
import com.atrosys.platform.model.to.Tag;
import com.atrosys.platform.model.to.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Kasra on 1/28/2018.
 */
@Service("daysService")
public class DaysServiceImpl implements DaysService{
    @Autowired
    DaysRepository daysRepository;

    @Override
    public void save(Days days) {
        daysRepository.save(days);
    }

    @Override
    public List<Days> findAllByUser(User user) {
        return daysRepository.findAllByUser(user);
    }

    @Override
    public boolean isWorkingTimeForUser(User user) {



        return daysRepository.findByUserAndAndDayLikeAndAndStartHourIsLessThanEqualAndEndHourGreaterThanEqual(user, getDay(), getTime(), getTime()) != null;
    }

    @Override
    public List<User> findAvailableUsers(List<Tag> tags) {

        List<User> users = new ArrayList<>();
        boolean cont;
        for (Days days:daysRepository.findByDayLikeAndStartHourIsLessThanEqualAndEndHourGreaterThanEqual(getDay(),getTime(),getTime())){
            if (days.getUser().getTags().size()!=0){
                cont = true;
            }else cont = false;
            if (tags!=null){
                    for (Tag tag:tags) {
                        if (!cont){
                            break;
                        }
                       for (Tag t:days.getUser().getTags()){
                           if (t.equals(tag)){
                               users.add(days.getUser());
                               cont = false;
                               break;
                           }
                       }
                    }

            }else
            users.add(days.getUser());
        }

        return users;
    }
    private int getTime(){
        int start = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int startMin = Calendar.getInstance().get(Calendar.MINUTE);
        String s;
        if (startMin<10){
            s = start+"0"+startMin;
        }else {
            s = start+""+startMin;
        }
        return Integer.parseInt(s);
    }
    private String getDay(){
        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        String today = "";
        switch (day){
            case 1:
                today = Days.SU;
                break;
            case 2:
                today = Days.MO;
                break;
            case 3:
                today = Days.TU;
                break;
            case 4:
                today = Days.WE;
                break;
            case 5:
                today = Days.TH;
                break;
            case 6:
                today = Days.FR;
                break;
            case 7:
                today = Days.SA;
                break;
        }
        return today;
    }
}
