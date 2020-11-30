package com.cab.bookings.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cab.bookings.model.Driver;

@Repository
public interface CabBookingsService extends JpaRepository<Driver, Integer> {

	@Transactional
	@Modifying
	@Query(value = "UPDATE DRIVER d set d.longitude = :longitude and d.latitude = :latitude where d.id = :id", nativeQuery = true)
	int setLocation(@Param("longitude") Double longitude, @Param("latitude") Double latitude, @Param("id") int id);

	// Driver save(Driver details);
}
