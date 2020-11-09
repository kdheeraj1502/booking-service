package com.cab.bookings.util;

import java.util.ArrayList;
import java.util.List;

import com.cab.bookings.model.AvaialbleCabs;
import com.cab.bookings.model.Driver;
import com.cab.bookings.model.Location;

public class HaversineUtil {
	
	public static List<AvaialbleCabs> findNearCab(Location location, List<Driver> details) {
		List<AvaialbleCabs> available = new ArrayList<>();
		for(Driver dr : details) {
			if(distance(location, dr.getLatitude(), dr.getLongitude()) <= 4) {
				AvaialbleCabs ac = new AvaialbleCabs();
				ac.setName(dr.getName());
				ac.setCarNumber(dr.getCar_number());
				ac.setPhone(dr.getPhone_number());
				available.add(ac);
			}
		}
		return available;
	}

	public static double distance(Location location, double lat2, double lon2) {
		double dLat = Math.toRadians(lat2 - location.getLatitude());
		double dLon = Math.toRadians(lon2 - location.getLongitude());

		double lat = Math.toRadians(location.getLatitude());
		lat2 = Math.toRadians(lat2);

		double a = Math.pow(Math.sin(dLat / 2), 2) + 
				Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat) * Math.cos(lat2);
		double rad = 6371;
		double c = 2 * Math.asin(Math.sqrt(a));
		return rad * c;
	}
}