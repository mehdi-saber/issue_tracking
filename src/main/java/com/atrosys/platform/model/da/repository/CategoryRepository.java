package com.atrosys.platform.model.da.repository;

import com.atrosys.platform.model.to.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by asgari on 12/27/17.
 */
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findById(int id);
    Category findByTitle(String title);
    List<Category> findByTitleStartsWith(String title);
}
