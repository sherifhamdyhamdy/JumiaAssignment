package com.jumia.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.demo.error.CustomGlobalExceptionHandler;
import com.jumia.demo.model.CustomerDto;
import com.jumia.demo.model.CustomerResponse;
import com.jumia.demo.model.Pager;
import com.jumia.demo.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class SuperHeroControllerMockMvcStandaloneTest {

    private MockMvc mvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    // This object will be magically initialized by the initFields method below.
    private JacksonTester<CustomerResponse> customerResponse;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use the MockitoExtension
        // MockitoAnnotations.initMocks(this);
        // Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new CustomGlobalExceptionHandler())
                //.addFilters(new SuperHeroFilter())
                .build();
    }

    @Test
    public void canRetrieveByIdWhenExists() throws Exception {
        // given
        given(customerService.getCustomers(null, "All", 1)).willReturn(getCustomerResponseObj());

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/customers")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        System.out.println("sss=" + response.getContentAsString());
        assertThat(response.getContentAsString()).isEqualTo(customerResponse.write(getCustomerResponseObj()).getJson());
    }

    @Test
    public void test_getCustomers_wrongState_failed() throws Exception {
        // given
        //given(customerService.getCustomers(null,"zzzz",1)).willReturn(getCustomerResponseObj());

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/customers/?state=mmm")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn()
                .getResponse();

        // then
        assertThat(response.getContentAsString()).isEqualTo("");
    }


    private CustomerResponse getCustomerResponseObj() {
        CustomerResponse customerResponseTest = new CustomerResponse();
        Pager pager = new Pager();
        pager.setNumberOfPages(4);
        customerResponseTest.setPager(pager);

        List<CustomerDto> customerDtoList = new ArrayList<CustomerDto>();
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCountry("Moracco");
        customerDto.setCountryCode("002");
        customerDto.setName("Sherif");
        customerDto.setNumber("123");
        customerDto.setState("Valid");
        customerDtoList.add(customerDto);
        customerResponseTest.setCustomerDtoList(customerDtoList);
        return customerResponseTest;
    }

}