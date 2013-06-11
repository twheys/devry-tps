package edu.devry.cis470.tps.service.dto;

public class UpdateStaffRequest {

	private Integer yearsExperience;
	private String educationLevel;
	private Integer desiredSalary;
	private String city;
	private byte[] pictureData;

	public String getCity() {
		return city;
	}

	public Integer getDesiredSalary() {
		return desiredSalary;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public byte[] getPictureData() {
		return pictureData;
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

	public void setPictureData(final byte[] pictureData) {
		this.pictureData = pictureData;
	}

	public void setYearsExperience(final Integer yearsExperience) {
		this.yearsExperience = yearsExperience;
	}
}
