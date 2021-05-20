package com.jumia.demo.utils;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConstantTest {

    @Test
    public void test_Constant_ConstructorIsPrivate_success() throws NoSuchMethodException {
        Constructor<Constant> constructor = Constant.class.getDeclaredConstructor();

        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    }

    @Test
    public void test_Constant_ThrowException_success() throws NoSuchMethodException {
        Constructor<Constant> constructor = Constant.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        assertThrows(InvocationTargetException.class, () -> {
            constructor.newInstance();
        });
    }

}