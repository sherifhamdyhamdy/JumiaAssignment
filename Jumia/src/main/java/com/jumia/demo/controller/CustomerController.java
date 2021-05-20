package com.jumia.demo.controller;

import com.jumia.demo.model.CustomerResponse;
import com.jumia.demo.service.CustomerService;
import com.jumia.demo.utils.Constant;
import com.jumia.demo.validation.ValuesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Validated
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "customers", method = RequestMethod.GET)

    public ResponseEntity<CustomerResponse> getCustomers(@RequestParam(required = false) String country,
                                                         @RequestParam(required = false, defaultValue = "All")
                                                     @ValuesAllowed(values = {Constant.ALL,Constant.VALID,Constant.NOT_VALID}) String state,
                                                         @RequestParam(required = false, defaultValue = "1") int page) {
        CustomerResponse customerResponse = customerService.getCustomers(country, state, page);
        return new ResponseEntity<CustomerResponse>(customerResponse, HttpStatus.OK);
    }
}
