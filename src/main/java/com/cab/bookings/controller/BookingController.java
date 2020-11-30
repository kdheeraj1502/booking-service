package com.cab.bookings.controller;

import com.cab.bookings.model.AvaialbleCabs;
import com.cab.bookings.model.Driver;
import com.cab.bookings.model.Location;
import com.cab.bookings.service.CabBookingsService;
//import com.cab.bookings.service.CabBookingsServiceImpl;
import com.cab.bookings.util.DriverResponseUtil;
import com.cab.bookings.util.HaversineUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/api/v1")
public class BookingController {

	@Autowired
	private CabBookingsService bookingService;
	
	Map<String, Object> output;
	
	public BookingController(){
		output = new HashMap<>();
	}

	@PostMapping(value = "/driver/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registerDriver(@RequestBody(required = true) Driver details) {
		try {
			Driver saved = bookingService.save(details);
			return new ResponseEntity<Object>(DriverResponseUtil.registerDriverUtil(saved, saved.getId()),
					HttpStatus.CREATED);
		} catch (Exception ex) {
			output.put("status", "failure");
			output.put("reason", "details missing");
			return new ResponseEntity<>(output, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/driver/{id}/sendLocation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getLocation(@PathVariable(value = "id", required = true) int id,
			@RequestBody(required = true) Location location) {
		if (bookingService.findById(id).isPresent()) {
			bookingService.setLocation(location.getLongitude(), location.getLongitude(), id);
			output.put("status", "success");
			return new ResponseEntity<>(output, HttpStatus.OK);
		} else {
			output.put("status", "failure");
			output.put("reason", "id not found");
			return new ResponseEntity<>(output, HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping(value = "/passenger/available_cabs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> nearCabs(@RequestBody(required = true) Location location) {
		try {
			List<AvaialbleCabs> cabs = HaversineUtil.findNearCab(location, bookingService.findAll());
			if (cabs != null) {
				if (cabs.size() == 0) {
					output.put("message", "No cabs available!");
					return new ResponseEntity<>(output, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(cabs, HttpStatus.OK);
				}
			}
			else {
				output.put("status", "failure");
				return new ResponseEntity<>(output, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
			output.put("status", "failure");
			output.put("reason", "wrong request");
			return new ResponseEntity<>(output, HttpStatus.BAD_REQUEST);
		}
	}
}
