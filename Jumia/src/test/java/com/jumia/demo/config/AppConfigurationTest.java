package com.jumia.demo.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = AppConfiguration.class)
@TestPropertySource("classpath:application-test.properties")
class AppConfigurationTest {

    @Autowired
    AppConfiguration appConfiguration;

    @Test
    void getNumberOfRowsPerPage() {

        System.out.println(appConfiguration.getNumberOfRowsPerPage());

        assertEquals(10,appConfiguration.getNumberOfRowsPerPage());
    }
}