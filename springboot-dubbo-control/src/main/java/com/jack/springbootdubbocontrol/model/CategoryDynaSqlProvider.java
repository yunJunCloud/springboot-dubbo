package com.jack.springbootdubbocontrol.model;

import com.jack.bean.Moview;
import com.jack.bean.User;

import java.util.Map;

public class CategoryDynaSqlProvider {
    public String list() {
        return "select * from user";
    }

    public String get(String key) {
        return "select * from user where userName = #{key} ";
    }

    public String delete(Integer uid) {
        return "delete * from user where uid=#{uid}";
    }

    public String add(User user) {
        return "insert into user values('" + user.getUserName() + "')";
    }

    public String update(Map<String, String> param) {
        return "update user set ";
    }

    /**
     * 获取角色信息
     */
    public String getRole() {
        return "select * from role";
    }

    /**
     * 插入电影
     */
    public String addMoview(Moview moview){
        return "insert into `moview`(`name`,`url`,`type`) values ("+"'"+moview.name+"'"+","+"'"+moview.url+"'"+","+moview.type+")";
    }

    private static String MOVIE_FILED="`id`,`name`,`url`,`type`";
    /**
     * 根据名称查询电影
     */
    public String serachMovieByName(String name){
        name = "'%"+name+"%'";
        return "select" +MOVIE_FILED+"from moview where `name` like "+name;
    }

    /**
     * 根据名称查询电影
     */
    public String serachMovie(){
        return "select " +MOVIE_FILED+" from moview";
    }

    /**
     * 根据地址查询电影
     */
    public String serachMovieByUrl(String url){
        return "select " +MOVIE_FILED+" from moview where `url`=#{url}";
    }
}
