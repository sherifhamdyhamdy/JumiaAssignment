package com.jumia.demo.utils;

import java.util.stream.Stream;

public enum CountryCodeEnum {
	
	CAM("Cameroon","+237","\\(237\\)\\ ?[2368]\\d{7,8}$"),
	ETH("Ethiopia","+251","\\(251\\)\\ ?[1-59]\\d{8}$"),
	MOR("Morocco","+212","\\(212\\)\\ ?[5-9]\\d{8}$"),
	MOZ("Mozambique","+258","\\(258\\)\\ ?[28]\\d{7,8}$"),
	AGU("Uganda","+256","\\(256\\)\\ ?\\d{9}$");
    String name;
    String code;
    String reg;
	
    CountryCodeEnum(String name, String code,String reg) {
		this.name=name;
		this.code=code;
		this.reg=reg;
	}

	public static Stream<CountryCodeEnum> stream() {
		return Stream.of(CountryCodeEnum.values());
	}

	String getCode() {
		return this.code;
	}
	
	String getName()
	{
		return this.name;
	}
	
	String getReg()
	{
		return this.reg;
	}
	
	
	

}
