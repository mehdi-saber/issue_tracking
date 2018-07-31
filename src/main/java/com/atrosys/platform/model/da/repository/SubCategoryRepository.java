package com.atrosys.platform.model.da.repository;

import com.atrosys.platform.model.to.Category;
import com.atrosys.platform.model.to.SubCategory;
import com.atrosys.platform.model.to.Tag;
import com.atrosys.platform.model.to.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by asgari on 12/27/17.
 */
public interface SubCategoryRepository extends JpaRepository<SubCategory,Integer> {
    List<SubCategory> findByCategory(Category category);
    SubCategory findByTitle(String title);
    List<SubCategory> findByTags(List<Tag> tags);

}
