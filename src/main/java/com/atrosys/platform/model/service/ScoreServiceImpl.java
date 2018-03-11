package com.atrosys.platform.model.service;

import com.atrosys.platform.model.da.repository.ScoreRepository;
import com.atrosys.platform.model.to.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Kasra on 2/13/2018.
 */
@Service("scoreService")
public class ScoreServiceImpl implements ScoreService{
    private ScoreRepository scoreRepository;
    @Autowired
    public ScoreServiceImpl(ScoreRepository scoreRepository){
        this.scoreRepository = scoreRepository;
    }
    @Override
    public void saveOrUpdate(Score score) {
        scoreRepository.save(score);
    }
}
