package com.cab.bookings.util;

import com.cab.bookings.model.ApiResponse;
import com.cab.bookings.model.Driver;
import com.cab.bookings.model.RegisteredDriver;

public class DriverResponseUtil {

	public static ApiResponse registerDriverUtil(Driver details, int id) {
		RegisteredDriver rd = new RegisteredDriver();
		rd.setCar_number(details.getCar_number());
		rd.setLicense_number(details.getLicense_number());
		rd.setEmail(details.getEmail());
		rd.setName(details.getName());
		rd.setPhone_number(details.getPhone_number());
		rd.setId(id);
		return rd;
	}
}
