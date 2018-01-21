package com.atrosys.platform.model.service;

import com.atrosys.platform.model.da.repository.RoleRepository;
import com.atrosys.platform.model.to.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by asgari on 1/8/18.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository repository;
    @Override
    public List<Role> findAllRoles() {

        return repository.findAll();
    }
}
