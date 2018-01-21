package com.atrosys.platform.model.service;

import com.atrosys.platform.model.da.repository.CategoryRepository;
import com.atrosys.platform.model.to.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by asgari on 1/14/18.
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category findCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

    @Override
    public int getCategoriesCount() {
        return (int) categoryRepository.count();
    }

    @Override
    public List<Category> findAllByLimit(int offset, int limit) {
        return categoryRepository.findAll(new OffsetBasedPageRequest(offset,limit)).getContent();
    }

    @Override
    public List<Category> searchCategoryByTitle(String title) {
        return categoryRepository.findByTitleStartsWith(title);
    }
}
