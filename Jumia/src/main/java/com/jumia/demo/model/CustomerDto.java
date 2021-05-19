package com.jumia.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
	private String id;
	private String name;
	private String state;
	private String country;
	private String countryCode;
	private String number;
}
