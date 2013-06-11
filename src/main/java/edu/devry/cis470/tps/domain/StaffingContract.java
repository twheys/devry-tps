package edu.devry.cis470.tps.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class StaffingContract extends IdEntity {

	private Long desiredSalary;
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Staff> desiredStaff;
	private String location;
	@ManyToOne
	private Client client;

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

	@Override
	public String toString() {
		return "StaffingContract [id=" + super.getId() + ", location="
				+ location + ", desiredSalary=" + desiredSalary + "]";
	}
}
