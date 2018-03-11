package com.atrosys.platform.model.da.repository;

import com.atrosys.platform.model.to.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



public interface RoleRepository extends JpaRepository<Role, Integer>{
    Role findByRole(String role);

}
