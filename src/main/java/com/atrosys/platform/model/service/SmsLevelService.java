package com.atrosys.platform.model.service;

import com.atrosys.platform.model.to.SmsLevel;

import java.util.List;

public interface SmsLevelService
{
    void save(SmsLevel smsLevel);
    SmsLevel findSmsLevelById(int id);
    List<SmsLevel> findAllSmsLevels();
    SmsLevel findSmsLevelByTitle(String title);
    int getSmsLevelsCount();
    List<SmsLevel> findAllByLimit(int offset, int limit);
    List<SmsLevel> searchSmsLevelByTitle(String title);
}
