package com.jumia.demo.service;

import com.jumia.demo.entity.Customer;
import com.jumia.demo.model.CustomerDto;
import com.jumia.demo.model.CustomerResponse;
import com.jumia.demo.model.Pager;
import com.jumia.demo.repository.CustomerReporistory;
import com.jumia.demo.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    CustomerReporistory customerReporistory;

    public CustomerResponse getCustomers(String country, String state, int page) {
        System.out.println("getCustomers called="+country+", "+state+", "+page);
        CustomerResponse customerResponses = new CustomerResponse();
        List<CustomerDto> customerDtoModelList = new ArrayList<CustomerDto>();
        List<Customer> customerList = customerReporistory.findAll();
        System.out.println("customerList.size="+customerList.size());
        customerDtoModelList = applyFilter(country, state, customerList);
        System.out.println("customerDtoModelList.size="+customerDtoModelList.size());
        applyPagination(customerDtoModelList, page, customerResponses);
        return customerResponses;
    }

    private CustomerResponse applyPagination(List<CustomerDto> customerDtoList, int page, CustomerResponse customerRes) {
        int numberOfRowsPerPage = 10;
        int pageNumber = 1;
        if (customerDtoList.size() <= numberOfRowsPerPage) {
            return getCustomerResponse(customerDtoList, pageNumber, customerRes);
        }

        List<CustomerDto> customerDtoListFinal = sliceCustomerModelList(customerDtoList, page, numberOfRowsPerPage);


        pageNumber = (int) Math.ceil((float) customerDtoList.size() / numberOfRowsPerPage);

        return getCustomerResponse(customerDtoListFinal, pageNumber, customerRes);
    }


    private List<CustomerDto> sliceCustomerModelList(List<CustomerDto> customerDtoList, int page, int numberOfRowsPerPage) {
        int startIndex = (numberOfRowsPerPage * page) - numberOfRowsPerPage;
        int endIndex = getEndIndex(page, numberOfRowsPerPage, customerDtoList);
        List<CustomerDto> customerDtoListFinal = customerDtoList.stream()
                .collect(Collectors.toList()).subList(startIndex, endIndex);
        return customerDtoListFinal;
    }


    private CustomerResponse getCustomerResponse(List<CustomerDto> customerDtoList, int pageNumber, CustomerResponse customerRes) {
        Pager pager = new Pager();
        pager.setNumberOfPages(pageNumber);
        customerRes.setCustomerDtoList(customerDtoList);
        customerRes.setPager(pager);
        return customerRes;
    }

    private int getEndIndex(int page, int numberOfRowsPerPage, List<CustomerDto> customerDtoList) {
        int index = numberOfRowsPerPage * page;
        if (index > customerDtoList.size()) {
            return customerDtoList.size();
        }

        return index;
    }


    private List<CustomerDto> applyFilter(String country, String state, List<Customer> customerList) {
        List<CustomerDto> customerDtoModelList = CommonUtils.mapCustomerListToCustomerModelList(customerList).stream()
                .filter(!CommonUtils.isEmptyString(country) ?
                        (customerDto -> customerDto.getCountry().toLowerCase().contains(country.toLowerCase())):
                        customerDto -> true)
                .filter(CommonUtils.isValidState(state) ?
                        (customerDto -> customerDto.getState().equalsIgnoreCase(state)):
                        customerDto -> true)
                .collect(Collectors.toList());
        return customerDtoModelList;
    }

}
