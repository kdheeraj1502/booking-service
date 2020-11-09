package com.cab.bookings.controller;

import com.cab.bookings.model.ApiResponse;
import com.cab.bookings.model.AvaialbleCabs;
import com.cab.bookings.model.Driver;
import com.cab.bookings.model.Location;
import com.cab.bookings.service.CabBookingsService;
import com.cab.bookings.service.CabBookingsServiceImpl;
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

	@Autowired
	private ApiResponse response;

	@PostMapping(value = "/driver/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> registerDriver(@RequestBody(required = true) Driver details) {
		try {
			Driver saved = bookingService.save(details);
			return new ResponseEntity<ApiResponse>(DriverResponseUtil.registerDriverUtil(saved, saved.getId()),
					HttpStatus.CREATED);
		} catch (Exception ex) {
			response.setStatus("failure");
			response.setReason("details missing");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/driver/{id}/sendLocation ", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> getLocation(@PathVariable(value = "id", required = true) int id,
			@RequestBody(required = true) Location location) {
		if (bookingService.findById(id).isPresent()) {
			bookingService.setLocation(location.getLongitude(), location.getLongitude(), id);
			response.setStatus("success");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatus("failure");
			response.setReason("id not found");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping(value = "/passenger/available_cabs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> nearCabs(@RequestBody(required = true)  Location location) {
		try {
			List<AvaialbleCabs> cabs = HaversineUtil.findNearCab(location, bookingService.findAll());
			if (response.getCabs().size() == 0) {
				response.setMessage("No cabs available!");
				return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
			} else {
				response.setCabs(cabs);
				return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
			}
		} catch (Exception ex) {
			response.setStatus("failure");
			response.setReason("wrong request");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
