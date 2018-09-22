package com.test.user.service;

import java.util.List;

import com.test.user.entity.User;

/**
 * User业务逻辑接口类
 *
 */
public interface UserService {

    /**
     * 列出所有用户信息
     */
    List<User> showAll();
    /**
     * 根据用户名称查询用户信息
     */
    List<User> selectByName(String name);
    /**
     * 增加用户信息
     * @return 
     */
    User selectByID(String id);
    

    public void addUser(User user);
    /**
     * 根据UserID更新用户信息
     */
    public void updateByID(User user);

    /**
     * 根据UserID删除用户信息
     * @param id
     * @return
     */
    public void deleteByID(String id);
  
}
