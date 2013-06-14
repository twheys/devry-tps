package edu.devry.cis470.tps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.devry.cis470.tps.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	Client findByUserName(String username);

}
