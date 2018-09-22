package com.test.user.entity;


import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Getter;
import lombok.Setter;

/**
 * User实体类
 *
 */

@Setter
@Getter
@JsonAutoDetect
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	
    private String id;

    private String name;
 
    private Date birthday;

    private String description;
    
    private String redisKey;
    
   
    
    @Override
    public String toString(){
    	return "User{"+
    			"id="+id+
    			",name="+name+
    			",birthday="+birthday+
    			",description="+description+"}";
    }

}
