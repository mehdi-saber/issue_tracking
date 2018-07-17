package com.atrosys.platform.model.service;

import com.atrosys.platform.model.to.EmailLevel;

import java.util.List;

public interface EmailLevelService
{
    void save(EmailLevel emailLevel);
    EmailLevel findEmailLevelById(int id);
    List<EmailLevel> findAllEmailLevels();
    EmailLevel findEmailLevelByTitle(String title);
    int getEmailLevelsCount();
    List<EmailLevel> findAllByLimit(int offset, int limit);
    List<EmailLevel> searchEmailLevelByTitle(String title);
}
