package com.jumia.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.demo.error.CustomGlobalExceptionHandler;
import com.jumia.demo.model.CustomerResponse;
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

import static com.jumia.demo.utils.TestUtil.getCustomerResponseObj;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    private MockMvc mvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private JacksonTester<CustomerResponse> customerResponse;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new CustomGlobalExceptionHandler())
                .build();
    }

    @Test
    public void canRetrieveByIdWhenExists() throws Exception {
        given(customerService.getCustomers(null, "All", 1)).willReturn(getCustomerResponseObj());
        MockHttpServletResponse response = mvc.perform(
                get("/customers")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(customerResponse.write(getCustomerResponseObj()).getJson());
    }

    @Test
    public void test_getCustomers_wrongState_failed() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                get("/customers/?state=mmm")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).isEqualTo("");
    }


}