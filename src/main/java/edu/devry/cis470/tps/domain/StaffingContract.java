package edu.devry.cis470.tps.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import edu.devry.cis470.tps.controller.dto.ContractStatus;

@Entity
public class StaffingContract extends IdEntity {

	private Long desiredSalary;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Staff> desiredStaff;
	private String location;
	private ContractStatus status;
	@ManyToOne
	private Client client;

	public StaffingContract() {
		status = ContractStatus.NEW;
	}

	public Client getClient() {
		return client;
	}

	public Long getDesiredSalary() {
		return desiredSalary;
	}

	public List<Staff> getDesiredStaff() {
		return desiredStaff;
	}

	public String getLocation() {
		return location;
	}

	public ContractStatus getStatus() {
		return status;
	}

	public void setClient(final Client client) {
		this.client = client;
	}

	public void setDesiredSalary(final Long desiredSalary) {
		this.desiredSalary = desiredSalary;
	}

	public void setDesiredStaff(final List<Staff> desiredStaff) {
		this.desiredStaff = desiredStaff;
	}

	public void setLocation(final String location) {
		this.location = location;
	}

	public void setStatus(final ContractStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "StaffingContract [id=" + super.getId() + ", location="
				+ location + ", desiredSalary=" + desiredSalary + "]";
	}
}
