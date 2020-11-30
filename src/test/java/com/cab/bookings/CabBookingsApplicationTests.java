package com.cab.bookings;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cab.bookings.controller.BookingController;

@SpringBootTest
class CabBookingsApplicationTests {
	
	@Autowired
	BookingController controller;
	
	@Test
	public void registerDriverTest() {
	}

	@Test
	public void getLocationTest() {
		
	}
	
	@Test
	public void nearCabsTest() {
		
	}
}
