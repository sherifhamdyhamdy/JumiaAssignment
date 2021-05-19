package com.jumia.demo.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.demo.JumiaApplication;
import com.jumia.demo.model.CustomerDto;
import com.jumia.demo.model.CustomerResponse;
import com.jumia.demo.model.Pager;
import com.jumia.demo.service.CustomerService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = JumiaApplication.class)
@RunWith(MockitoJUnitRunner.class)

public class CustomerControllerTest_1 {

    @InjectMocks
    private CustomerController jumiaController;

    @Mock
    private CustomerService customerService;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        this.mvc = MockMvcBuilders.standaloneSetup(jumiaController).build();
    }

    @Test
    public void testLoadPhoneNumbers() throws Exception {
        CustomerResponse customerResponseTest=new CustomerResponse();
        Pager pager=new Pager();
        pager.setNumberOfPages(4);
        customerResponseTest.setPager(pager);

        List<CustomerDto> customerDtoList=new ArrayList<CustomerDto>();
        CustomerDto customerDto=new CustomerDto();
        customerDto.setCountry("Moracco");
        customerDto.setCountryCode("002");
        customerDto.setName("Sherif");
        customerDto.setNumber("123");
        customerDto.setState("Valid");
        customerDtoList.add(customerDto);
        customerResponseTest.setCustomerDtoList(customerDtoList);

        this.mvc = MockMvcBuilders.standaloneSetup(jumiaController).build();
        when(customerService.getCustomers("Morocoo","not",2)).thenReturn(customerResponseTest);
        MvcResult mvcResult=mvc.perform(get("/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString=mvcResult.getResponse().getContentAsString();
        System.out.println("sss="+contentAsString);
        //customerMockList=mapFromJson(contentAsString,List.class);
        //Assertions.assertEquals(customerMockList.size(),0);
    }

    private <T> T mapFromJson(String json, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

}
