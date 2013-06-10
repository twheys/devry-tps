package edu.devry.cis470.tps.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Client extends User {

	@OneToMany(fetch = FetchType.LAZY)
	private List<StaffingContract> contracts;

	public List<StaffingContract> getContracts() {
		return contracts;
	}

	public void setContracts(final List<StaffingContract> contracts) {
		this.contracts = contracts;
	}

}
