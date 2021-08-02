package com.jumia.demo.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jumia.demo.utils.TestUtil.getCustomerResponseObj;
import static org.assertj.core.api.Assertions.assertThat;



@JsonTest
public class CustomerResponseTest {

    @Autowired
    private JacksonTester<CustomerResponse> customerResponse;

    @Test
    public void serialization() throws Exception
    {
        JsonContent<CustomerResponse> write = this.customerResponse.write(getCustomerResponseObj());
        assertThat(write).hasJsonPath("$.customerDtoList");
        assertThat(write).isEqualToJson(new ClassPathResource("SuccessResponse.json"));
    }


}
