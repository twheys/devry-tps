package edu.devry.cis470.tps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.devry.cis470.tps.domain.Client;
import edu.devry.cis470.tps.domain.StaffingContract;

@Repository
public interface StaffingContractRepository extends
		JpaRepository<StaffingContract, Long> {

	List<StaffingContract> findByClient(Client client);

}
