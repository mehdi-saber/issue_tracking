package com.atrosys.platform.model.da.repository;

import com.atrosys.platform.model.to.SmsLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SmsLevelRepository extends JpaRepository<SmsLevel,Integer> {
    SmsLevel findById(int id);
    SmsLevel findByTitle(String title);
    List<SmsLevel> findByTitleStartsWith(String title);
}
