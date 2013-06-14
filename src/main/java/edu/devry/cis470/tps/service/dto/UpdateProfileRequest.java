package edu.devry.cis470.tps.service.dto;


public class UpdateProfileRequest {

	private String firstName;
	private String lastName;
	private Integer yearsExperience;
	private String educationLevel;
	private Integer desiredSalary;
	private String city;

	public UpdateProfileRequest() {
		super();
	}

	public String getCity() {
		return city;
	}

	public Integer getDesiredSalary() {
		return desiredSalary;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
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

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public void setYearsExperience(final Integer yearsExperience) {
		this.yearsExperience = yearsExperience;
	}
}
