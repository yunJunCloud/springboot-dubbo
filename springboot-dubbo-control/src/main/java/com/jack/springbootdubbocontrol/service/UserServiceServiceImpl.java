package com.jack.springbootdubbocontrol.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jack.bean.User;
import com.jack.interfaces.IUserService;
import com.jack.springbootdubbocontrol.model.IUserModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceServiceImpl implements IUserService {

    @Autowired
    private IUserModel iUserModel;

    @Override
    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        /*User user = this.addUser(new User());
        userList.add(user);*/
        userList = iUserModel.userList();
        return userList;
    }

    @Override
    public User addUser(User user) {
        user = new User();
        user.setUid(1);
        user.setUserName("jack");
        return user;

    }

    @Override
    public User getUserByKey(Object object) {
        User user = new User();
        user = iUserModel.userBykey((String) object);
        return user;
    }

    @Override
    public void deleteUserById(String id) {

    }
}
