/**
 * 
 */
package com.test.user.redisConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Administrator
 *
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{
	/**
	 * 注入RedisConnectionFactory
	 */
	@Autowired
	RedisConnectionFactory redisConnectionFactory;
	/**
	 * 实例化RedisTemplate对象
	 */
	@Bean
	public RedisTemplate<String,Object> functionDomainRedisTemplate(){
		RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String, Object>();//Redis模板，<key,value>数据操作
		initDomainRedisTemplate(redisTemplate,redisConnectionFactory);
		return redisTemplate;
	}
	/**
	 * 设置数据存入redis的序列化方式
	 * @param redisTemplate
	 * @param redisConnectionFactory
	 */
	private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate,RedisConnectionFactory factory) {
		
		// TODO Auto-generated method stub
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());//key,value（当value为对象的时候）及hash key，value进行序列化
		redisTemplate.setConnectionFactory(factory);		
	}
	
	/**
	 * 缓存 管理器
	 * CacheManager提供getCahce类及getCacheName类接口
	 * @param redisTemplate
	 * @return
	 */
	@Bean
	public CacheManager cacheManager(RedisTemplate<?,?> redisTemplate) {
		CacheManager cacheManager = new RedisCacheManager(redisTemplate);
		return cacheManager;
	}
	/**
	 * 实例化HashOperations对象，可以使用Hash类型操作
	 * @param redisTemplate
	 * @return
	 */
	@Bean
	public HashOperations<String,String,Object> hashOperations(RedisTemplate<String,Object> redisTemplate){
		
		return redisTemplate.opsForHash();
		
	}
	/**
	 * 实例化ValueOperations对象，可使用String操作
	 * @param redisTemplate
	 * @return
	 */
	@Bean
	public ValueOperations<String,Object> valueOperations(RedisTemplate<String,Object> redisTemplate){
		
		return redisTemplate.opsForValue();
		
	}
	/**
	 * 实例化ListOperations对象，可使用List操作
	 * @param redisTemplate
	 * @return
	 */
	@Bean
	public ListOperations<String,Object> listOperations(RedisTemplate<String,Object> redisTemplate){
		return redisTemplate.opsForList();
	}
	/**
	 * 实例化SetOperations对象，可使用Set操作
	 * @param redisTemplate
	 * @return
	 */
	@Bean
	public SetOperations<String,Object> setOperations(RedisTemplate<String,Object> redisTemplate){
		
		return redisTemplate.opsForSet();
	}
	/**
	 * 实例化ZSetOperations对象，可使用ZSet操作
	 * @param redisTemplate
	 * @return
	 */
	@Bean
	public ZSetOperations<String,Object> zSetOperations(RedisTemplate<String,Object> redisTemplate){
		
		return redisTemplate.opsForZSet();
	}
	

}
