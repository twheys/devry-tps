package edu.devry.cis470.tps.service;

import edu.devry.cis470.tps.domain.Staff;
import edu.devry.cis470.tps.service.dto.UpdateStaffRequest;
import edu.devry.cis470.tps.service.impl.NonUniqueUsernameException;

public interface StaffService {

	Staff createNewStaff(String username, String password)
			throws NonUniqueUsernameException;

	Staff updateStaff(Long staffId, UpdateStaffRequest request);
}
