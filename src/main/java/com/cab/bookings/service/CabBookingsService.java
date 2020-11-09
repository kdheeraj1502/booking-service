package com.cab.bookings.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cab.bookings.model.Driver;

@Repository
public interface CabBookingsService extends JpaRepository<Driver, Integer> {
	
	@Modifying
	@Query("update DRIVER d set d.latitude = ?1 and d.longitude = ?2 where id = ?3")
	int setLocation(Double firstname, Double lastname, int id);

	Driver save(Driver details);
}
