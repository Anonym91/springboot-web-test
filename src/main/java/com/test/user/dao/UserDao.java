 package com.test.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.test.user.entity.User;

/**
 * User DAO 接口类
 *
 */
@Mapper // 标志为 Mybatis 的 Mapper
public interface UserDao {

    /**
     * 列出所有用户信息
     *
     * @param name 用户名
     */
    @Select("SELECT * FROM testuser ORDER BY UserName")
    // 返回所有User信息
    @Results({
            @Result(property = "id", column = "UserID"),
            @Result(property = "name", column = "UserName"),
            @Result(property = "birthday", column = "UserBirth"),
            @Result(property = "description", column = "Descript")})
    List<User> showAll();
    
    /**
     * 根据UserName查询用户信息
     * @param name
     * @return
     */
    @Select("SELECT * FROM testuser WHERE UserName=#{name}")
    //根据UserName查询User信息（名称可能会重复，不止一个，因此返回user对象的list）
    @Results({
        @Result(property = "id", column = "UserID"),
        @Result(property = "name", column = "UserName"),
        @Result(property = "birthday", column = "UserBirth"),
        @Result(property = "description", column = "Descript")})
    List<User> selectByName(String name);
    

 
    @Select("SELECT * FROM testuser WHERE UserID=#{id}")
    //根据ID查询User信息（唯一ID所以返回单个user对象）
    @Results({
        @Result(property = "id", column = "UserID"),
        @Result(property = "name", column = "UserName"),
        @Result(property = "birthday", column = "UserBirth"),
        @Result(property = "description", column = "Descript")})
    User selectByID(String id);
    
    
    
    /**
     * 插入新User
     * @param user
     * @return
     */
    @SelectKey(keyProperty = "id",keyColumn="UserID",resultType = String.class, before =true, statement = "select replace(uuid(), '-', '-')" )
    //插入用户信息，使用自生成的uuid码作为UserID
    @Options(keyProperty = "id", useGeneratedKeys = true)
    @Insert("INSERT INTO testuser(UserID,UserName,UserBirth,Descript)"
    		+ "VALUES(#{id},#{name},#{birthday},#{description})")
    public void addUser(User user);
    
    /**
     * 根据用户ID更新用户信息（ID唯一标识）
     * @param user
     */
    
    @Update("UPDATE testuser SET UserName=#{name},UserBirth=#{birthday},Descript=#{description} WHERE UserID=#{id}")
    @Results({
        @Result(property = "id", column = "UserID"),
        @Result(property = "name", column = "UserName"),
        @Result(property = "birthday", column = "UserBirth"),
        @Result(property = "description", column = "Descript")})
    public void updateByID(User user);
    
    /**
     * 根据用户ID删除用户信息（ID唯一标识）
     * @param id
     * @return
     */
    @Delete("DELETE FROM testuser WHERE UserID=#{id}")
    public void deleteByID(String id);
    
    
}
