package edu.devry.cis470.tps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import edu.devry.cis470.tps.domain.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long>,
		QueryDslPredicateExecutor<Staff> {

}
