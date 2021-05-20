package com.jumia.demo.service;

import com.jumia.demo.entity.Customer;
import com.jumia.demo.model.CustomerDto;
import com.jumia.demo.model.CustomerResponse;
import com.jumia.demo.model.Pager;
import com.jumia.demo.repository.CustomerReporistory;
import com.jumia.demo.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerService {

    @Autowired
    CustomerReporistory customerReporistory;


    @Value("#{new Integer('${app.numberOfRowsPerPage}')}")
    private Integer numberOfRowsPerPage;

    public CustomerResponse getCustomers(String country, String state, int page) {
        CustomerResponse customerResponses = new CustomerResponse();
        List<CustomerDto> customerDtoModelList = new ArrayList<CustomerDto>();
        List<Customer> customerList = customerReporistory.findAll();
        customerDtoModelList = applyFilter(country, state, customerList);
        applyPagination(customerDtoModelList, page, customerResponses);
        return customerResponses;
    }

    private CustomerResponse applyPagination(List<CustomerDto> customerDtoList, int page, CustomerResponse customerRes) {
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
        customerRes.setCustomerDtoList(customerDtoList);
        customerRes.setPager(fillPager(customerDtoList, pageNumber));
        return customerRes;
    }


    private Pager fillPager(List<CustomerDto> customerDtoList, int pageNumber) {
        Pager pager = new Pager();
        pager.setNumberOfPages(pageNumber);
        pager.setTotalCount(customerDtoList !=null? customerDtoList.size():0);
        pager.setTotalDisplayedRows(numberOfRowsPerPage);
        return pager;
    }

    private int getEndIndex(int page, int numberOfRowsPerPage, List<CustomerDto> customerDtoList) {
        int index = numberOfRowsPerPage * page;
        if (index > customerDtoList.size()) {
            return customerDtoList.size();
        }

        return index;
    }


    private List<CustomerDto> applyFilter(String country, String state, List<Customer> customerList) {
        List<CustomerDto> customerDtoModelList = CommonUtils.mapCustomerListToCustomerDtolList(customerList).stream()
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
