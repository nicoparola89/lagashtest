package com.lagash.test.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagash.test.domain.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class CustomerRepositoryRedis {
    private HashOperations hashOperations;

    private ValueOperations valueOperations;

    private RedisTemplate redisTemplate;

    @Value("${app.timeexpire.cache}")
    private Integer timeExpire;

    public CustomerRepositoryRedis(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
        this.valueOperations = this.redisTemplate.opsForValue();
    }

    public void save(Customer user){
        System.out.println(timeExpire);
       valueOperations.set(user.getId(),user,timeExpire,TimeUnit.SECONDS);
    }

    public Customer findById(String id){
        return (Customer)valueOperations.get(id);
    }

    public void update(Customer user){
        save(user);
    }



}
