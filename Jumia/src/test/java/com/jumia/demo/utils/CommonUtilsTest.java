package com.jumia.demo.utils;

import com.jumia.demo.entity.Customer;
import com.jumia.demo.model.CustomerDto;
import org.assertj.core.error.AssertionErrorMessagesAggregator;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonUtilsTest {

    @Test
    public void test_CommonUtils_ConstructorIsPrivate_success() throws NoSuchMethodException {
        Constructor<CommonUtils> constructor = CommonUtils.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, () -> {
            constructor.newInstance();
        });
    }

    @Test
    void test_isEmptyString_passString_success() {
        assertFalse(CommonUtils.isEmptyString("test"));
    }

    @Test
    void test_isEmptyString_passNull_success() {
        assertTrue(CommonUtils.isEmptyString(null));
    }

    @Test
    void test_isEmptyString_passEmpty_success() {
        assertTrue(CommonUtils.isEmptyString(""));
    }

    @Test
    void test_isEmptyString_passBlank_success() {
        assertTrue(CommonUtils.isEmptyString(" "));
    }

    @Test
    void test_isValidState_passAll_success() {
        assertFalse(CommonUtils.isValidState("All"));
    }

    @Test
    void test_isValidState_passNull_success() {
        assertFalse(CommonUtils.isValidState(null));
    }


    @Test
    void test_mapCustomerListToCustomerModelList_success() {
        List<Customer> cutomerList = new ArrayList<Customer>();
        Customer customer = new Customer();
        customer.setId(123);
        customer.setName("Walid Hammadi");
        customer.setPhone("(212) 6007989253");
        cutomerList.add(customer);
        List<CustomerDto> customerDtoList = CommonUtils.mapCustomerListToCustomerModelList(cutomerList);
        assertEquals("123", customerDtoList.get(0).getId());
    }

    @Test
    void test_getState_NotValidNumber_success()throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        Method privateMethod = CommonUtils.class.getDeclaredMethod("getState",Customer.class,String.class);
        privateMethod.setAccessible(true);
        Customer customer = new Customer();
        customer.setId(123);
        customer.setName("Walid Hammadi");
        customer.setPhone("(212) 6007989253");

        String state =(String) privateMethod.invoke(CommonUtils.class,customer,"\\(212\\)\\ ?[5-9]\\d{8}$");
        assertEquals(Constant.NOT_VALID,state);
    }

    @Test
    void test_getState_ValidNumber_success()throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        Method privateMethod = CommonUtils.class.getDeclaredMethod("getState",Customer.class,String.class);
        privateMethod.setAccessible(true);
        Customer customer = new Customer();
        customer.setId(123);
        customer.setName("Yosaf Karrouch");
        customer.setPhone("(212) 698054317");

        String state =(String) privateMethod.invoke(CommonUtils.class,customer,"\\(212\\)\\ ?[5-9]\\d{8}$");
        assertEquals(Constant.VALID,state);
    }


}