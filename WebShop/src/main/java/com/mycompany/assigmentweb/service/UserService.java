package com.mycompany.assigmentweb.service;

import com.mycompany.assigmentweb.model.User;
import java.util.List;

public interface UserService {

    User findById(int id);

    User findByUserName(String userName);

    void saveUser(User user);

    void updateUser(User user);

    void deleteByUserName(String userName);

    List<User> findAllUsers();

    boolean isUserNameUnique(Integer id, String userName);
}
