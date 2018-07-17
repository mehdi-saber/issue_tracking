package com.atrosys.platform.model.da.repository;

import com.atrosys.platform.model.to.CausesOfFailure;
import com.atrosys.platform.model.to.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CausesOfFailureRepository extends JpaRepository<CausesOfFailure,Integer> {
    List<CausesOfFailure> findBySubCategory(SubCategory subCategory);
    CausesOfFailure findByTitle(String title);
}
