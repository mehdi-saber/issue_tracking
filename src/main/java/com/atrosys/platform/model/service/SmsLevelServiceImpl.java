package com.atrosys.platform.model.service;

import com.atrosys.platform.model.da.repository.SmsLevelRepository;
import com.atrosys.platform.model.to.SmsLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("smsLevelService")
public class SmsLevelServiceImpl implements SmsLevelService {
    @Autowired
    SmsLevelRepository smsLevelRepository;
    @Override
    public void save(SmsLevel smsLevel) {
        smsLevelRepository.save(smsLevel);
    }

    @Override
    public SmsLevel findSmsLevelById(int id) {
        return smsLevelRepository.findById(id);
    }

    @Override
    public List<SmsLevel> findAllSmsLevels() {
        return smsLevelRepository.findAll();
    }

    @Override
    public SmsLevel findSmsLevelByTitle(String title) {
        return smsLevelRepository.findByTitle(title);
    }

    @Override
    public int getSmsLevelsCount() {
        return (int) smsLevelRepository.count();
    }

    @Override
    public List<SmsLevel> findAllByLimit(int offset, int limit) {
        return smsLevelRepository.findAll(new OffsetBasedPageRequest(offset,limit)).getContent();
    }

    @Override
    public List<SmsLevel> searchSmsLevelByTitle(String title) {
        return smsLevelRepository.findByTitleStartsWith(title);
    }
}
