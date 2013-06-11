package edu.devry.cis470.tps.domain;

public enum EducationLevel {
	HIGH_SCHOOL(0), ASSOCIATES(1), BACHELORS(2), MASTERS(3), DOCTORATES(4);

	private int order;

	EducationLevel(final int order) {
		this.order = order;
	}

	public int getOrder() {
		return order;
	}
}
