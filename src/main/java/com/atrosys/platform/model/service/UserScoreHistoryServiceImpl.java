package com.atrosys.platform.model.service;

import com.atrosys.platform.model.da.repository.UserScoreHistoryRepository;
import com.atrosys.platform.model.to.UserScoreHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Kasra on 1/28/2018.
 */
@Service("userScoreHistoryService")
public class UserScoreHistoryServiceImpl implements UserScoreHistoryService{
    @Autowired
    UserScoreHistoryRepository userScoreHistoryRepository;
    @Override
    public void save(UserScoreHistory userScoreHistory) {
        userScoreHistoryRepository.save(userScoreHistory);
    }
}
