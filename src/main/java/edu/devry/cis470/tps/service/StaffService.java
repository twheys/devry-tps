package edu.devry.cis470.tps.service;

import edu.devry.cis470.tps.domain.Staff;
import edu.devry.cis470.tps.service.dto.UpdateStaffRequest;

public interface StaffService {

	Staff createNewStaff(String username, String password);

	Staff updateStaff(UpdateStaffRequest request);
}
