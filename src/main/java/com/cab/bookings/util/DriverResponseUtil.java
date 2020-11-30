package com.cab.bookings.util;

import java.util.*;

import com.cab.bookings.model.Driver;
import com.cab.bookings.model.RegisteredDriver;

public class DriverResponseUtil {

	public static Map<String, Object> registerDriverUtil(Driver details, int id) {
		RegisteredDriver rd = null;
		if(details != null) {
			rd = new RegisteredDriver();
			rd.setCar_number(details.getCar_number());
			rd.setLicense_number(details.getLicense_number());
			rd.setEmail(details.getEmail());
			rd.setName(details.getName());
			rd.setPhone_number(details.getPhone_number());
			rd.setId(id);
			rd.setStatus("Success");
		}
		return response(rd);
	}
	
	public static Map<String, Object> response(RegisteredDriver rd){
		Map<String, Object> output = null;
		if(rd != null) {
			output = new HashMap<>();
			output.put("id", rd.getId());
			output.put("name", rd.getName());
			output.put("email", rd.getEmail());
			output.put("phone number", rd.getPhone_number());
			output.put("license number", rd.getLicense_number());
			output.put("car number", rd.getCar_number());
			output.put("status", rd.getStatus());
		}
		
		return output;
	}
}
