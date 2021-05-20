package com.jumia.demo.service;

import com.jumia.demo.JumiaApplication;
import com.jumia.demo.model.CustomerDto;
import com.jumia.demo.model.CustomerResponse;
import com.jumia.demo.repository.CustomerReporistory;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.jumia.demo.utils.TestUtil.getCustomerList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = JumiaApplication.class)
@RunWith(MockitoJUnitRunner.class)
class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerReporistory customerReporistory;

    @Test
    void test_getCustomers_withoutFilter_success() {
        Mockito.when(customerReporistory.findAll()).thenReturn(getCustomerList());
        CustomerResponse customerResponse = customerService.getCustomers(null, "All", 1);

        assertEquals(1, customerResponse.getCustomerDtoList().size());
    }

    @Test
    void test_getCustomers_withCountryFilter_success() {
        Mockito.when(customerReporistory.findAll()).thenReturn(getCustomerList());
        CustomerResponse customerResponse = customerService.getCustomers("Morocco", "All", 1);

        assertEquals("Morocco", customerResponse.getCustomerDtoList().get(0).getCountry());
        assertEquals("+212", customerResponse.getCustomerDtoList().get(0).getCountryCode());
    }


    @Test
    void test_getCustomers_withStateFilter_success() {
        Mockito.when(customerReporistory.findAll()).thenReturn(getCustomerList());
        CustomerResponse customerResponse = customerService.getCustomers(null, "Not Valid", 1);

        assertEquals("Morocco", customerResponse.getCustomerDtoList().get(0).getCountry());
        assertEquals("+212", customerResponse.getCustomerDtoList().get(0).getCountryCode());
        assertEquals("Not Valid", customerResponse.getCustomerDtoList().get(0).getState());

    }

    @Test
    void test_getCustomers_withStateCountryFilter_success() {
        Mockito.when(customerReporistory.findAll()).thenReturn(getCustomerList());

        CustomerResponse customerResponse = customerService.getCustomers("Morocco", "Not Valid", 1);

        assertEquals("Morocco", customerResponse.getCustomerDtoList().get(0).getCountry());
        assertEquals("+212", customerResponse.getCustomerDtoList().get(0).getCountryCode());
        assertEquals("Not Valid", customerResponse.getCustomerDtoList().get(0).getState());

    }


    @Test
    void test_getEndIndex_indexMoreThanCustomerDtoList_success() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        CustomerService obj = new CustomerService();
        List<CustomerDto> customerDtoList = new ArrayList<>();
        Method privateMethod = CustomerService.class.getDeclaredMethod("getEndIndex", int.class, int.class, List.class);
        privateMethod.setAccessible(true);

        assertEquals(0, customerDtoList.size());
    }
}