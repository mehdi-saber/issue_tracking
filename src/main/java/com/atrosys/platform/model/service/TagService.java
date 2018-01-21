package com.atrosys.platform.model.service;

import com.atrosys.platform.model.to.Tag;

import java.util.List;

/**
 * Created by asgari on 12/27/17.
 */
public interface TagService {
    void save(Tag tag);
    Tag findByTagName(String name);
    Tag findByTagId(int id);
    List<Tag> findAllTags();
}
