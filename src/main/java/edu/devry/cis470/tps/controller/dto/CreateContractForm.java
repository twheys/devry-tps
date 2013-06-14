package edu.devry.cis470.tps.controller.dto;

import java.util.List;

public class CreateContractForm {

	private Long desiredSalary;
	private String location;
	private List<Long> candidates;

	public CreateContractForm() {
		super();
	}

	public CreateContractForm(final SearchForm form) {
		desiredSalary = form.getSalary();
		location = form.getCity();
	}

	public List<Long> getCandidates() {
		return candidates;
	}

	public Long getDesiredSalary() {
		return desiredSalary;
	}

	public String getLocation() {
		return location;
	}

	public void setCandidates(final List<Long> candidates) {
		this.candidates = candidates;
	}

	public void setDesiredSalary(final Long desiredSalary) {
		this.desiredSalary = desiredSalary;
	}

	public void setLocation(final String location) {
		this.location = location;
	}
}
