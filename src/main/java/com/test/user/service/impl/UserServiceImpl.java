package com.test.user.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import com.test.user.dao.UserDao;
import com.test.user.entity.User;
import com.test.user.service.UserService;

/**
 * UserService逻辑实现类
 *
 */
@Service("UserService")
//@CacheConfig(cacheNames="userCache")
//@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;
    
    @Resource
    private RedisServiceImpl redisService;
    
//    @Autowired
//    private RedisTemplate redisTemplate;
//    
    /**
     * 获取User逻辑：如缓存存在则从换从取值，否则从DB中获取，插入缓存    
     */
    

//   @Cacheable(value="userCache") 返回所有对象的方法，不应使用缓存？其他更新操作只添加\修改一个对象，此方法为返回对象集合，不会随着其他缓存中某一个对象更新改变
    public List<User> showAll() {
    
    	return userDao.showAll();
    }
    
    
    @Cacheable(value="userCache",key="#name")//插入缓存，以name为key，对应user为value
    public List<User> selectByName(String name){
    	List<User> user= userDao.selectByName(name);
       	return  user;
    }
    
    @Cacheable(value="userCache",key="#id")//插入缓存，以id为key，对应user为value
    public User selectByID(String id) {

    	User user=userDao.selectByID(id);
    	
    	return user;    	
    }
    

   @Caching(put= {
		   @CachePut(value="userCache",key="#user.id"),  
		   @CachePut(value="userCache",key="#user.name") 
   }) //添加新user对象时，分别在redis数据库中增加以id及name作为key的user缓存  
    public void addUser(User user){
    	userDao.addUser(user);
  	 	  	
    }
   @Caching(
		   evict= {
		   @CacheEvict(value="userCache",key="#user.id",allEntries=false),
		   @CacheEvict(value="userCache",key="#user.name",allEntries=false)
		   
   }) //引入多层缓存清除：根据id（Redis：key）更新用户信息后，同步的更新id对应的name（Redis：key）查询条件的缓存对象（Redis：value）  
    public void updateByID(User user) {

    	 userDao.updateByID(user);
    }
   
   @Caching(
		   evict= {
		   @CacheEvict(value="userCache",key="#id",allEntries=false),
		   @CacheEvict(value="userCache",key="#user.name",allEntries=true)
		   //user.name这边如果设置为false，操作过后会报name找不到错误，暂不清楚原因
		   
   }) //引入多层缓存清除：根据id删除用户信息后，同步的删除id对应的name查询条件的缓存
    public void deleteByID(String id) {
	   
    	 userDao.deleteByID(id);    	 
    	 
    }

}
