package com.atrosys.platform.model.service;

import com.atrosys.platform.model.da.repository.TagRepository;
import com.atrosys.platform.model.to.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by asgari on 1/13/18.
 */
@Service("tagService")
public class TagServiceImpl implements TagService {
    @Autowired
    TagRepository tagRepository;
    @Override
    public void save(Tag tag) {
      tagRepository.save(tag);
    }

    @Override
    public Tag findByTagName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public Tag findByTagId(int id) {
        return tagRepository.findById(id);
    }

    @Override
    public List<Tag> findAllTags() {
        return tagRepository.findAll();
    }
}
