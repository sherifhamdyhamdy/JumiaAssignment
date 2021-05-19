package com.jumia.demo.mapper;
import com.jumia.demo.entity.Customer;
import com.jumia.demo.model.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {


    CustomerDto CustomerToCustomerDto(Customer customer);

}
