package com.atrosys.platform.model.da.repository;

import com.atrosys.platform.model.to.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by asgari on 12/27/17.
 */
public interface TagRepository extends JpaRepository<Tag,Integer> {
    Tag findByName(String name);
    Tag findById(int id);
}
