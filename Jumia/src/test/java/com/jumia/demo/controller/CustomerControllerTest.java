package com.jumia.demo.controller;

import com.jumia.demo.model.CustomerDto;
import com.jumia.demo.model.CustomerResponse;
import com.jumia.demo.model.Pager;
import com.jumia.demo.service.UserService;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //@MockBean
    //private UserService userService;
    //@Mock
    ///private UserService userService;
    //@InjectMocks
    //private UserService userService;
   // @Autowired
    //private WebApplicationContext context;
    @BeforeEach
    void setMockOutput() {

        UserService userService = Mockito.mock(UserService.class);
        //this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
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

        when(userService.getCustomers(anyString(),anyString(),anyInt())).thenReturn(new CustomerResponse());
    }

    
    @Test
    void test_getUsers_returnResponse_success() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .get("/users"))
               // .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerDtoList").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pager").exists());
    }


    @Test
    void test_getUsers_invalidState_success() throws Exception {
        UserService userService = Mockito.mock(UserService.class);
        when(userService.getCustomers(null,"not",3)).thenReturn(new CustomerResponse());
        mockMvc.perform(MockMvcRequestBuilders
                .get("/users"))
                // .andDo(print())
                .andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.customerDtoList").exists())
               .andExpect(MockMvcResultMatchers.jsonPath("$.pager").exists());
    }

}