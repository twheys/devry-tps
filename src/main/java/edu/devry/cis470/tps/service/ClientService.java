package edu.devry.cis470.tps.service;

import java.util.List;

import edu.devry.cis470.tps.domain.Client;
import edu.devry.cis470.tps.domain.Staff;
import edu.devry.cis470.tps.domain.StaffingContract;
import edu.devry.cis470.tps.service.dto.BrowseRequest;
import edu.devry.cis470.tps.service.dto.StaffingContractRequest;

public interface ClientService {

	List<Staff> browseCandidates(BrowseRequest request);

	Client createNewClient(String username, String password);

	StaffingContract createStaffingContract(StaffingContractRequest request);

	List<StaffingContract> getAllStaffingContracts(Long clientId);

	StaffingContract getStaffingContract(Long contractId);
}
