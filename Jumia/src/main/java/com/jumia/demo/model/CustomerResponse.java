package com.jumia.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
public class CustomerResponse {
    private List<CustomerDto> customerDtoList;
    private Pager pager;
}
