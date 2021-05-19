package com.jumia.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jumia.demo.entity.Customer;

@Repository
public interface  CustomerReporistory extends JpaRepository<Customer, Integer> {

}
