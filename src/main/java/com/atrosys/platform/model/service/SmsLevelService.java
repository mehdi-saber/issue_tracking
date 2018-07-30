package com.atrosys.platform.model.service;

import com.atrosys.platform.model.to.SmsLevel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SmsLevelService
{
    void save(SmsLevel smsLevel);
    SmsLevel findSmsLevelById(int id);
//    SmsLevel findBy
    List<SmsLevel> findAllSmsLevels();
    SmsLevel findSmsLevelByTitle(String title);
    int getSmsLevelsCount();
    List<SmsLevel> findAllByLimit(int offset, int limit);
    List<SmsLevel> searchSmsLevelByTitle(String title);
}
