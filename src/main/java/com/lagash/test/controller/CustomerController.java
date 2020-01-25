package com.lagash.test.controller;

import com.lagash.test.domain.Customer;
import com.lagash.test.service.impl.CustomerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerServiceImpl customerServiceImpl;

    public CustomerController(CustomerServiceImpl customerServiceImpl){
        this.customerServiceImpl = customerServiceImpl;
    }
    @GetMapping("/random")
    public ResponseEntity<Customer> getCustomerRandom(){

        return ResponseEntity.ok(customerServiceImpl.getCustomerRandom());

    }
}
