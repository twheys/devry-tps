package edu.devry.cis470.tps.service;

import java.io.IOException;
import java.io.InputStream;

import edu.devry.cis470.tps.domain.Staff;
import edu.devry.cis470.tps.service.dto.UpdateProfileRequest;
import edu.devry.cis470.tps.service.impl.NonUniqueUsernameException;
import edu.devry.cis470.tps.service.impl.NotFoundException;

public interface StaffService {

	Staff createNewStaff(String username, String password, String email)
			throws NonUniqueUsernameException;

	Staff getStaff(String userName) throws NotFoundException;

	Staff updateStaff(String userName, UpdateProfileRequest request);

	Staff updateStaffPicture(String userName, InputStream stream,
			String contentType) throws IOException;
}
