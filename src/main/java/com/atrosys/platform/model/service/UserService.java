package com.atrosys.platform.model.service;

import com.atrosys.platform.model.to.User;

import java.util.List;

/**
 * Created by asgari on 12/24/17.
 */
public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
    List<User> findAll();
    List<User> findAllByLimit(int offset,int limit);
    int getUsersCount();
    List<User> searchUserByNameOrFamilyOrEmail(String search);
    User findUserById(int id);
    void updateUser(User user);
    List<User> findActiveUsersExceptClients();
}
