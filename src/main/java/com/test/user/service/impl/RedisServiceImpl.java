/**
 * 
 */
package com.test.user.service.impl;

import org.springframework.stereotype.Service;

import com.test.user.entity.User;
import com.test.user.service.RedisService;

/**
 * @author Administrator
 *
 */
@Service("RedisService")
public class RedisServiceImpl extends RedisService<User>{
	
	private static final String REDIS_KEY="USER_KEY";
	
	protected String getRedisKey() {
		
		return REDIS_KEY;
	}


}
