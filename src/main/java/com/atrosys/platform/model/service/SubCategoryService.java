package com.atrosys.platform.model.service;

import com.atrosys.platform.model.to.Category;
import com.atrosys.platform.model.to.SubCategory;

import java.util.List;

/**
 * Created by asgari on 12/27/17.
 */
public interface SubCategoryService {
    void save(SubCategory subCategory);
    List<SubCategory> findSubCategoriesByParent(Category category);
    SubCategory findSubCategoryByTitle(String title);
}
