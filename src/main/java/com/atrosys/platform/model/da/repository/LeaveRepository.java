package com.atrosys.platform.model.da.repository;

import com.atrosys.platform.model.to.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Kasra on 7/24/2018.
 */
public interface LeaveRepository extends JpaRepository<Leave , Integer> {
    Leave findByName(String name);
    Leave findById(int id);
}
