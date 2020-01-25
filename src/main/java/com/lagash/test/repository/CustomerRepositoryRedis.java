package com.lagash.test.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagash.test.domain.Customer;
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

    public CustomerRepositoryRedis(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
        this.valueOperations = this.redisTemplate.opsForValue();
    }

    public void save(Customer user){
       valueOperations.set(user.getId(),user,300,TimeUnit.SECONDS);
       // hashOperations.put("USER", user.getId(), user);
        // redisTemplate.expire(user.getId(),300, TimeUnit.SECONDS);
    }
    public List findAll(){

        return hashOperations.values("USER");
    }

    public Customer findById(String id){
        ObjectMapper objectMapper = new ObjectMapper();
        return (Customer)valueOperations.get(id);
        //return (Customer) hashOperations.get("USER", id);
    }

    public void update(Customer user){
        save(user);
    }

    public void delete(String id){
        hashOperations.delete("USER", id);
    }

}
