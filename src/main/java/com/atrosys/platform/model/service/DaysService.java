package com.atrosys.platform.model.service;

import com.atrosys.platform.model.to.Days;
import com.atrosys.platform.model.to.Tag;
import com.atrosys.platform.model.to.User;

import java.util.List;


/**
 * Created by Kasra on 1/28/2018.
 */

public interface DaysService {
    void save(Days days);
    List<Days> findAllByUser(User user);
    boolean isWorkingTimeForUser(User user);
    List<User> findAvailableUsers(List<Tag> tags);
}
