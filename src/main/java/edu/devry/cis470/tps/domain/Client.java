package edu.devry.cis470.tps.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.google.common.collect.Lists;

@Entity
public class Client extends User {

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<StaffingContract> contracts;

	public Client() {
		contracts = Lists.newArrayList();
	}

	public List<StaffingContract> getContracts() {
		return contracts;
	}

	public void setContracts(final List<StaffingContract> contracts) {
		this.contracts = contracts;
	}

}
