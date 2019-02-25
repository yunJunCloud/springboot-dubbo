package com.jack.interfaces;

import com.jack.bean.User;

import java.util.List;

public interface IUserService {
    List<User> getUserList();
    User addUser(User user);
    User getUserByKey(Object object);
    void deleteUserById(String id );
}
