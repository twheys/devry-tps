package edu.devry.cis470.tps.service.dto;

import java.util.List;

public class StaffingContractRequest {

	private List<Integer> staffIds;
	private String city;
	private Integer desiredSalary;

	public String getCity() {
		return city;
	}

	public Integer getDesiredSalary() {
		return desiredSalary;
	}

	public List<Integer> getStaffIds() {
		return staffIds;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public void setDesiredSalary(final Integer desiredSalary) {
		this.desiredSalary = desiredSalary;
	}

	public void setStaffIds(final List<Integer> staffIds) {
		this.staffIds = staffIds;
	}
}
