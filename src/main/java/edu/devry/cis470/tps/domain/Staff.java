package edu.devry.cis470.tps.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

@Entity
public class Staff extends User {

	private String firstName;
	private String lastName;
	private String city;
	private Integer desiredSalary;
	@Enumerated(EnumType.STRING)
	private EducationLevel educationLevel;
	@Lob
	private byte[] picture;
	private Integer yearsExperience;

	public Staff() {
		educationLevel = EducationLevel.UNKNOWN;
	}

	public String getCity() {
		return city;
	}

	public Integer getDesiredSalary() {
		return desiredSalary;
	}

	public EducationLevel getEducationLevel() {
		return educationLevel;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public byte[] getPicture() {
		return picture;
	}

	public Integer getYearsExperience() {
		return yearsExperience;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public void setDesiredSalary(final Integer desiredSalary) {
		this.desiredSalary = desiredSalary;
	}

	public void setEducationLevel(final EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public void setPicture(final byte[] picture) {
		this.picture = picture;
	}

	public void setYearsExperience(final Integer yearsExperience) {
		this.yearsExperience = yearsExperience;
	}

}
