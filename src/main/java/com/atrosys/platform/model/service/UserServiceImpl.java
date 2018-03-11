package com.atrosys.platform.model.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.atrosys.platform.model.to.Role;
import com.atrosys.platform.model.to.User;
import com.atrosys.platform.model.da.repository.RoleRepository;
import com.atrosys.platform.model.da.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }
    @Override
    public List<User> findAll(){



        return userRepository.findAll();
    }
    @Override
    public List<User> findAllByLimit(int offset,int limit){

        return userRepository.findAll(new OffsetBasedPageRequest(offset,limit)).getContent();
    }
    @Override
    public int getUsersCount(){
        return (int) userRepository.count();
    }

    @Override
    public List<User> searchUserByNameOrFamilyOrEmail(String search) {
        return userRepository.findByNameStartsWithOrLastNameStartsWithOrEmailStartsWith(search,search,search);
    }

    @Override
    public User findUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findActiveUsersExceptClients() {
        return userRepository.findByActiveTrueAndRolesNotContains(roleRepository.findByRole("CLIENT"));
    }


}
