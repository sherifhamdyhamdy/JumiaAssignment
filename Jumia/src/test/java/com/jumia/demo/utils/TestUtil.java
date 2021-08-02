package com.jumia.demo.utils;

import com.jumia.demo.entity.Customer;
import com.jumia.demo.model.CustomerDto;
import com.jumia.demo.model.CustomerResponse;
import com.jumia.demo.model.Pager;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    public static CustomerResponse getCustomerResponseObj() {
        CustomerResponse customerResponseTest = new CustomerResponse();



        List<CustomerDto> customerDtoList = new ArrayList<CustomerDto>();
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCountry("Moracco");
        customerDto.setCountryCode("002");
        customerDto.setName("Sherif");
        customerDto.setNumber("123");
        customerDto.setState("Valid");
        customerDtoList.add(customerDto);
        customerResponseTest.setCustomerDtoList(customerDtoList);

        Pager pager = new Pager();
        pager.setTotalCount(0);
        pager.setNumberOfPages(4);
        pager.setTotalDisplayedRows(0);
        customerResponseTest.setPager(pager);
        return customerResponseTest;
    }

    public static List<Customer> getCustomerList() {
        List<Customer> customerList = new ArrayList<Customer>();
        Customer customer = new Customer();
        customer.setId(123);
        customer.setName("Walid Hammadi");
        customer.setPhone("(212) 6007989253");
        customerList.add(customer);
        return customerList;
    }

    public static Customer getNotValidCustomer() {
        Customer customer = new Customer();
        customer.setId(123);
        customer.setName("Walid Hammadi");
        customer.setPhone("(212) 6007989253");
        return customer;
    }

    public static Customer getValidCustomer() {
        Customer customer = new Customer();
        customer.setId(123);
        customer.setName("Yosaf Karrouch");
        customer.setPhone("(212) 698054317");
        return customer;
    }

    public static List<CustomerDto> getCustomerDtoList() {
        List<CustomerDto> customerDtoList = new ArrayList<CustomerDto>();
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCountry("Moracco");
        customerDto.setCountryCode("002");
        customerDto.setName("Sherif");
        customerDto.setNumber("123");
        customerDto.setState("Valid");
        customerDtoList.add(customerDto);
        return customerDtoList;
    }
}
