package com.cab.bookings.model;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ApiResponse {

	String status;
	String reason;
	String message;
	List<AvaialbleCabs> cabs;

	public List<AvaialbleCabs> getCabs() {
		return cabs;
	}

	public void setCabs(List<AvaialbleCabs> cabs) {
		this.cabs = cabs;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
