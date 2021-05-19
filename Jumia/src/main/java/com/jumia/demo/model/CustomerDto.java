package com.jumia.demo.model;

import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
public class CustomerDto {
	public CustomerDto() {
	}
	@Getter
	@Setter
	private String id;

	private String name;
	@Getter
	@Setter
	private String state;
	@Getter
	@Setter
	private String country;
	@Getter
	@Setter
	private String countryCode;
	@Getter
	@Setter
	private String number;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
