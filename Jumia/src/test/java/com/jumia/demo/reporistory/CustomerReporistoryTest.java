package com.jumia.demo.reporistory;

import com.jumia.demo.entity.Customer;
import com.jumia.demo.repository.CustomerReporistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
public class CustomerReporistoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerReporistory customerReporistory;

    @Test
    void test_FindAll_emptyDB()
    {
        List<Customer> all = customerReporistory.findAll();
        assertThat(all).isEmpty();

    }

    @Test
    void test_FindAll_notEmptyDB()
    {
        Customer customer = entityManager.persistAndFlush(new Customer("sherif", "01067826201"));
        List<Customer> all = customerReporistory.findAll();
        assertThat(customer).isEqualTo(all.get(0));
    }

}
