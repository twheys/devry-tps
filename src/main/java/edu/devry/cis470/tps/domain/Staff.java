package edu.devry.cis470.tps.domain;

import javax.persistence.Entity;

@Entity
public class Staff extends User {

	private String city;
	private Integer desiredSalary;
	private String educationLevel;
	private byte[] picture;
	private Integer yearsExperience;

	public String getCity() {
		return city;
	}

	public Integer getDesiredSalary() {
		return desiredSalary;
	}

	public String getEducationLevel() {
		return educationLevel;
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

	public void setEducationLevel(final String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public void setPicture(final byte[] picture) {
		this.picture = picture;
	}

	public void setYearsExperience(final Integer yearsExperience) {
		this.yearsExperience = yearsExperience;
	}

}
