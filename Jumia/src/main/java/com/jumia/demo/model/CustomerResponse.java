package com.jumia.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerResponse {
    private List<CustomerDto> customerDtoList;
    private Pager pager;
}
