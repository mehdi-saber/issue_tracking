package com.atrosys.platform.model.service;

import com.atrosys.platform.model.to.Role;

import java.util.List;

/**
 * Created by asgari on 1/8/18.
 */
public interface RoleService {
    List<Role> findAllRoles();
}
