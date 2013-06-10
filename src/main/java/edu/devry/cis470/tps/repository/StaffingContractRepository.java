package edu.devry.cis470.tps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.devry.cis470.tps.domain.StaffingContract;

@Repository
public interface StaffingContractRepository extends
		JpaRepository<StaffingContract, Long> {

}
