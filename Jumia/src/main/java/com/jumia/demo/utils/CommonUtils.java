package com.jumia.demo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.jumia.demo.entity.Customer;
import com.jumia.demo.model.CustomerDto;

public class CommonUtils {
    public static final int PHONE_CODE_START_INDEX = 1;
    public static final int PHONE_CODE_END_INDEX = 4;
    public static final int PHONE_NUMBER_START_INDEX = 6;
	private CommonUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static List<CustomerDto> mapCustomerListToCustomerModelList(List<Customer> cutomerList) {
        List<CustomerDto> customerDtoList = new ArrayList<CustomerDto>();
        cutomerList.stream().forEach((Customer customer) -> {
            customerDtoList.add(mapCustomerToCustomerModel(customer));
        });

        return customerDtoList;
    }

    public static CustomerDto mapCustomerToCustomerModel(Customer customer) {
        String countryCode = "+" + customer.getPhone().substring(PHONE_CODE_START_INDEX, PHONE_CODE_END_INDEX);

        CustomerDto customerDtoModel = new CustomerDto();

        CountryCodeEnum.stream().filter(d -> d.getCode().equals(countryCode)).forEach((CountryCodeEnum CountryCode) -> {
            customerDtoModel.setId(Integer.toString(customer.getId()));
            customerDtoModel.setName(customer.getName());
            customerDtoModel.setCountryCode(CountryCode.getCode());
            customerDtoModel.setCountry(CountryCode.getName());
            customerDtoModel.setNumber(customer.getPhone().substring(PHONE_NUMBER_START_INDEX));
            customerDtoModel.setState(getState(customer, CountryCode.getReg()));

        });

        return customerDtoModel;
    }

    public static boolean isEmptyString(String paramStr) {
        if (paramStr == null || paramStr.isEmpty() || paramStr.isBlank()) {
			return true;
		}
        else {
			return false;
		}
    }

    private static String getState(Customer customer, String reg) {
        boolean state = Pattern.matches(reg, customer.getPhone());
        return state ? Constant.VALID : Constant.NOT_VALID;
    }


    public static boolean isValidState(String state) {
        boolean isValidState = true;
        if (isEmptyString(state) || Constant.ALL.equalsIgnoreCase(state)) {
            isValidState = false;
        }
        return isValidState;
    }
}


