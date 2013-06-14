package edu.devry.cis470.tps.service.dto;

import java.util.List;

public class StaffingContractRequest {

	private String city;
	private Long desiredSalary;
	private List<Long> staffIds;

	public String getCity() {
		return city;
	}

	public Long getDesiredSalary() {
		return desiredSalary;
	}

	public List<Long> getStaffIds() {
		return staffIds;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public void setDesiredSalary(final Long desiredSalary) {
		this.desiredSalary = desiredSalary;
	}

	public void setStaffIds(final List<Long> staffIds) {
		this.staffIds = staffIds;
	}

}
