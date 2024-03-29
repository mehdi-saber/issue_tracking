package com.atrosys.platform.model.service;

import com.atrosys.platform.model.da.repository.SubCategoryRepository;
import com.atrosys.platform.model.da.repository.TaskRepository;
import com.atrosys.platform.model.to.Category;
import com.atrosys.platform.model.to.SubCategory;
import com.atrosys.platform.model.to.Tag;
import com.atrosys.platform.model.to.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by asgari on 1/14/18.
 */
@Service("subCategoryService")
public class SubCategoryServiceImpl implements SubCategoryService {
    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Override
    public void save(SubCategory subCategory) {
        subCategoryRepository.save(subCategory);
    }

    @Override
    public List<SubCategory> findSubCategoriesByParent(Category category) {
        return subCategoryRepository.findByCategory(category);
    }

    @Override
    public SubCategory findSubCategoryByTitle(String title) {
        return subCategoryRepository.findByTitle(title);
    }

    @Override
    public List<SubCategory> findAllByTags(List<Tag> tags) {
        return subCategoryRepository.findByTags(tags);
    }


}
