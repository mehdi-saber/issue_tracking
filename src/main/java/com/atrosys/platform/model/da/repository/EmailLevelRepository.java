package com.atrosys.platform.model.da.repository;

import com.atrosys.platform.model.to.EmailLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailLevelRepository extends JpaRepository<EmailLevel,Integer> {
    EmailLevel findById(int id);
    EmailLevel findByTitle(String title);
    List<EmailLevel> findByTitleStartsWith(String title);
}
