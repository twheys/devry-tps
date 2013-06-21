package edu.devry.cis470.tps.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
public class Staff extends User {

	@Pattern(regexp = "^[a-zA-Z]*$", message = "Your first name must only contain letters.")
	@Length(min = 1, max = 56)
	private String firstName;

	@Pattern(regexp = "^[a-zA-Z]*$", message = "Your last name must only contain letters.")
	@Length(min = 1, max = 128)
	private String lastName;

	@Length(min = 1, max = 56)
	private String city;

	private Integer desiredSalary;

	@Enumerated(EnumType.STRING)
	private EducationLevel educationLevel;

	@OneToOne(cascade = CascadeType.ALL)
	private Picture picture;

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

	public Picture getPicture() {
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

	public void setPicture(final Picture picture) {
		this.picture = picture;
	}

	public void setYearsExperience(final Integer yearsExperience) {
		this.yearsExperience = yearsExperience;
	}

	@Override
	public String toString() {
		return "Staff [firstName=" + firstName + ", lastName=" + lastName
				+ ", city=" + city + ", desiredSalary=" + desiredSalary
				+ ", educationLevel=" + educationLevel + ", picture=" + picture
				+ ", yearsExperience=" + yearsExperience + "]";
	}

}
