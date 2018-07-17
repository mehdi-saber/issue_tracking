package com.atrosys.platform.model.service;

import com.atrosys.platform.model.da.repository.EmailLevelRepository;
import com.atrosys.platform.model.to.EmailLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("emailLevelService")
public class EmailLevelServiceImpl implements EmailLevelService {
    @Autowired
    EmailLevelRepository emailLevelRepository;
    @Override
    public void save(EmailLevel emailLevel) {
        emailLevelRepository.save(emailLevel);
    }

    @Override
    public EmailLevel findEmailLevelById(int id) {
        return emailLevelRepository.findById(id);
    }

    @Override
    public List<EmailLevel> findAllEmailLevels() {
        return emailLevelRepository.findAll();
    }

    @Override
    public EmailLevel findEmailLevelByTitle(String title) {
        return emailLevelRepository.findByTitle(title);
    }

    @Override
    public int getEmailLevelsCount() {
        return (int) emailLevelRepository.count();
    }

    @Override
    public List<EmailLevel> findAllByLimit(int offset, int limit) {
        return emailLevelRepository.findAll(new OffsetBasedPageRequest(offset,limit)).getContent();
    }

    @Override
    public List<EmailLevel> searchEmailLevelByTitle(String title) {
        return emailLevelRepository.findByTitleStartsWith(title);
    }
}
