package com.atrosys.platform.model;

import com.atrosys.platform.model.da.repository.CausesOfFailureRepository;
import com.atrosys.platform.model.da.repository.SubCategoryRepository;
import com.atrosys.platform.model.service.CausesOfFailureService;
import com.atrosys.platform.model.service.SubCategoryService;
import com.atrosys.platform.model.to.Category;
import com.atrosys.platform.model.to.CausesOfFailure;
import com.atrosys.platform.model.to.SubCategory;
import com.atrosys.platform.model.to.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("causesOfFailureService")
public class CausesOfFailureServiceImpl implements CausesOfFailureService {
    @Autowired
    CausesOfFailureRepository causesOfFailureRepository;
    @Override
    public void save(CausesOfFailure causesOfFailure) {
        causesOfFailureRepository.save(causesOfFailure);
    }

    @Override
    public List<CausesOfFailure> findCausesOfFailuresByParent(SubCategory subCategory) {
        return causesOfFailureRepository.findBySubCategory(subCategory);
    }

    @Override
    public CausesOfFailure findCausesOfFailureByTitle(String title) {
        return causesOfFailureRepository.findByTitle(title);
    }

}
