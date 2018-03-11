package com.atrosys.platform.model.da.repository;

import com.atrosys.platform.model.to.Days;
import com.atrosys.platform.model.to.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Kasra on 1/28/2018.
 */
public interface DaysRepository extends JpaRepository<Days,Integer> {
    List<Days> findAllByUser(User user);
    List<Days> findByDayLikeAndStartHourIsLessThanEqualAndEndHourGreaterThanEqual(String day,int start,int end);
    Days findByUserAndAndDayLikeAndAndStartHourIsLessThanEqualAndEndHourGreaterThanEqual(User user,String day,int start,int end);
}
