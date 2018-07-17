package com.atrosys.platform.model.service;

import com.atrosys.platform.model.to.CausesOfFailure;
import com.atrosys.platform.model.to.SubCategory;

import java.util.List;


public interface CausesOfFailureService {
    void save(CausesOfFailure causesOfFailure);
    List<CausesOfFailure> findCausesOfFailuresByParent(SubCategory subCategory);
    CausesOfFailure findCausesOfFailureByTitle(String title);
}
