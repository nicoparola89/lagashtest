package com.lagash.test.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagash.test.domain.Customer;
import com.lagash.test.repository.CustomerRepositoryRedis;
import com.lagash.test.service.CustomerService;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Value("${app.url.datasource}")
    private  String URL;
    private CustomerRepositoryRedis customerRepositoryRedis;

    private final CloseableHttpClient httpClient = HttpClients.createDefault();



    public CustomerServiceImpl(CustomerRepositoryRedis customerRepositoryRedis){
        this.customerRepositoryRedis = customerRepositoryRedis;
    }
    @Override
    public Customer getCustomerRandom() {

        HttpGet request = new HttpGet(URL);


        request.addHeader("x-api-key", "prueba");
        try (CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                String result = EntityUtils.toString(entity);
                List<Customer> customers = new ArrayList(Arrays.asList(objectMapper.readValue(result, Customer[].class)));
                Customer customerNew = getCustomerNotDB(customers);
                if(customerNew != null){
                    return customerNew;
                }else{
                    return null;
                }

            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Customer getCustomerNotDB(List<Customer> customerList){
        if(customerList.size() > 0){
            Random rand = new Random();
            int n = rand.nextInt(customerList.size());
            Customer customerRandom = customerList.get(n);
            Customer customerDB = customerRepositoryRedis.findById(customerRandom.getId());
            if(customerDB != null){
                customerList.remove(n);
                return getCustomerNotDB(customerList);
            }else{
                customerRepositoryRedis.save(customerRandom);
                return customerRandom;
            }
        }else{
            return null;
        }
    }
}
