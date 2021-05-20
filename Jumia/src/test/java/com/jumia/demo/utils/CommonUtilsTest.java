package com.jumia.demo.utils;

import com.jumia.demo.entity.Customer;
import com.jumia.demo.model.CustomerDto;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import static com.jumia.demo.utils.TestUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class CommonUtilsTest {

    @Test
    public void test_CommonUtils_ConstructorIsPrivate_success() throws NoSuchMethodException {
        Constructor<CommonUtils> constructor = CommonUtils.class.getDeclaredConstructor();

        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    }

    @Test
    public void test_CommonUtils_throwException_success() throws NoSuchMethodException {
        Constructor<CommonUtils> constructor = CommonUtils.class.getDeclaredConstructor();
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
        List<CustomerDto> customerDtoList = CommonUtils.mapCustomerListToCustomerDtolList(getCustomerList());

        assertEquals("Walid Hammadi", customerDtoList.get(0).getName());
    }

    @Test
    void test_getState_NotValidNumber_success() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method privateMethod = CommonUtils.class.getDeclaredMethod("getState", Customer.class, String.class);
        privateMethod.setAccessible(true);
        Customer customer = getNotValidCustomer();
        String state = (String) privateMethod.invoke(CommonUtils.class, customer, "\\(212\\)\\ ?[5-9]\\d{8}$");

        assertEquals(Constant.NOT_VALID, state);
    }


    @Test
    void test_getState_ValidNumber_success() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method privateMethod = CommonUtils.class.getDeclaredMethod("getState", Customer.class, String.class);
        privateMethod.setAccessible(true);
        Customer customer = getValidCustomer();
        String state = (String) privateMethod.invoke(CommonUtils.class, customer, "\\(212\\)\\ ?[5-9]\\d{8}$");

        assertEquals(Constant.VALID, state);
    }
}