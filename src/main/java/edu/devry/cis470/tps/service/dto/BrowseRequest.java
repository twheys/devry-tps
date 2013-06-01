package edu.devry.cis470.tps.service.dto;

public class BrowseRequest {

	private Integer minYearsExperience;
	private Integer maxYearsExperience;
	private String educationLevel;
	private Integer maximumSalary;
	private String city;

	public String getCity() {
		return city;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public Integer getMaximumSalary() {
		return maximumSalary;
	}

	public Integer getMaxYearsExperience() {
		return maxYearsExperience;
	}

	public Integer getMinYearsExperience() {
		return minYearsExperience;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public void setEducationLevel(final String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public void setMaximumSalary(final Integer maximumSalary) {
		this.maximumSalary = maximumSalary;
	}

	public void setMaxYearsExperience(final Integer maxYearsExperience) {
		this.maxYearsExperience = maxYearsExperience;
	}

	public void setMinYearsExperience(final Integer minYearsExperience) {
		this.minYearsExperience = minYearsExperience;
	}
}
