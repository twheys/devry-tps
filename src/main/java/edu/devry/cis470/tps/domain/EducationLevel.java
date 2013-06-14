package edu.devry.cis470.tps.domain;

public enum EducationLevel {
	UNKNOWN(0), HIGH_SCHOOL(1), ASSOCIATES(2), BACHELORS(3), MASTERS(4), DOCTORATES(
			5);

	private int order;

	EducationLevel(final int order) {
		this.order = order;
	}

	public String getDisplay() {
		switch (this) {
		case ASSOCIATES:
			return "Associates";
		case BACHELORS:
			return "Bachelors";
		case DOCTORATES:
			return "Doctorates";
		case HIGH_SCHOOL:
			return "High School";
		case MASTERS:
			return "Masters";
		case UNKNOWN:
		default:
			return "Unknown";
		}
	}

	public int getOrder() {
		return order;
	}

}
