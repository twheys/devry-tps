package edu.devry.cis470.tps.service;

import java.util.List;

import edu.devry.cis470.tps.domain.Client;
import edu.devry.cis470.tps.domain.Staff;
import edu.devry.cis470.tps.domain.StaffingContract;
import edu.devry.cis470.tps.service.dto.BrowseRequest;
import edu.devry.cis470.tps.service.dto.StaffingContractRequest;
import edu.devry.cis470.tps.service.impl.NonUniqueUsernameException;
import edu.devry.cis470.tps.service.impl.NotFoundException;

public interface ClientService {

	List<Staff> browseCandidates(BrowseRequest request);

	Client createNewClient(String username, String password, String email)
			throws NonUniqueUsernameException;

	StaffingContract createStaffingContract(final String userName,
			StaffingContractRequest request) throws NotFoundException;

	List<StaffingContract> getAllStaffingContract(String clientUserName)
			throws NotFoundException;

	StaffingContract getStaffingContract(Long contractId);
}
