package edu.devry.cis470.tps.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class StaffingContract {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;

	private String location;

	private Long desiredSalary;

	public Long getDesiredSalary() {
		return desiredSalary;
	}

	public Long getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}

	public void setDesiredSalary(final Long desiredSalary) {
		this.desiredSalary = desiredSalary;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setLocation(final String location) {
		this.location = location;
	}
}
