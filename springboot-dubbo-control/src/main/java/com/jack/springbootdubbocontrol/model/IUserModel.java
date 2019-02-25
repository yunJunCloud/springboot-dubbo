package com.jack.springbootdubbocontrol.model;

import com.jack.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface IUserModel {
    // @Select("select * from user where name = #{userName}")
    @SelectProvider(type = CategoryDynaSqlProvider.class,method = "list")
    List<User> userList();

    @SelectProvider(type = CategoryDynaSqlProvider.class,method = "get")
    User userBykey(String key);
}
