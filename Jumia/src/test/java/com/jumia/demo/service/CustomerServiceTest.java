package com.jumia.demo.service;

import com.jumia.demo.JumiaApplication;
import com.jumia.demo.config.AppConfiguration;
import com.jumia.demo.entity.Customer;
import com.jumia.demo.model.CustomerDto;
import com.jumia.demo.model.CustomerResponse;
import com.jumia.demo.model.Pager;
import com.jumia.demo.repository.CustomerReporistory;
import com.jumia.demo.utils.CommonUtils;
import com.jumia.demo.utils.Constant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.jumia.demo.utils.TestUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = JumiaApplication.class)
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @MockBean
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
        assertEquals(1,customerResponse.getPager().getTotalCount());
        assertEquals(1,customerResponse.getPager().getTotalCount());
        assertEquals(5,customerResponse.getPager().getTotalDisplayedRows());

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
        CustomerService obj = new CustomerService(new AppConfiguration());
        List<CustomerDto> customerDtoList = new ArrayList<>();
        Method privateMethod = CustomerService.class.getDeclaredMethod("getEndIndex", int.class, int.class, List.class);
        privateMethod.setAccessible(true);
        int index=(int)privateMethod.invoke(obj,1,1,customerDtoList);
        assertEquals(0, index);
    }

    @Test
    void test_fillPager() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method privateMethod = CustomerService.class.getDeclaredMethod("fillPager", List.class, int.class);
        privateMethod.setAccessible(true);
        List<CustomerDto> customers = getCustomerDtoList();
        System.out.println("xxxx="+customers.size());
        List<CustomerDto> customerDtoList = new ArrayList<>();
        CustomerService obj = new CustomerService(new AppConfiguration());
        Pager pager=(Pager)privateMethod.invoke(obj, customers, 1);

        assertEquals(1, pager.getTotalCount());

    }
}