package com.atrosys.platform.model.service;

import com.atrosys.platform.model.to.Category;

import java.util.List;

/**
 * Created by asgari on 12/27/17.
 */
public interface CategoryService
{
    void save(Category category);
    Category findCategoryById(int id);
    List<Category> findAllCategories();
    Category findCategoryByTitle(String title);
    int getCategoriesCount();
    List<Category> findAllByLimit(int offset,int limit);
    List<Category> searchCategoryByTitle(String title);
}
