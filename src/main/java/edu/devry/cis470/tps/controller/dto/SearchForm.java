package edu.devry.cis470.tps.controller.dto;

public class SearchForm {

	private Integer minYearsExp;
	private Integer maxYearsExp;
	private String educationLevel;
	private Long salary;
	private String city;

	public String getCity() {
		return city;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public Integer getMaxYearsExp() {
		return maxYearsExp;
	}

	public Integer getMinYearsExp() {
		return minYearsExp;
	}

	public Long getSalary() {
		return salary;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public void setEducationLevel(final String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public void setMaxYearsExp(final Integer maxYearsExp) {
		this.maxYearsExp = maxYearsExp;
	}

	public void setMinYearsExp(final Integer minYearsExp) {
		this.minYearsExp = minYearsExp;
	}

	public void setSalary(final Long salary) {
		this.salary = salary;
	}

}
