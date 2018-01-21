package com.atrosys.platform.model.da.repository;

import com.atrosys.platform.model.to.Role;
import com.atrosys.platform.model.to.Task;
import com.atrosys.platform.model.to.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByNameStartsWithOrLastNameStartsWithOrEmailStartsWith(String searchName,String searchLastName,String searchEmail);
    User findById(int id);
    List<User> findByActiveTrueAndRolesNotContains(Role role);




}
