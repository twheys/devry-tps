package edu.devry.cis470.tps.controller.dto;

public enum ContractStatus {
	UNKNOWN, NEW;

	public String getDisplay() {
		switch (this) {
		case NEW:
			return "New";
		case UNKNOWN:
		default:
			return "Unknown";
		}
	}
}
