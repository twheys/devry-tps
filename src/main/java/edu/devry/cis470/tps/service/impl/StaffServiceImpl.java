package edu.devry.cis470.tps.service.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.devry.cis470.tps.domain.EducationLevel;
import edu.devry.cis470.tps.domain.Staff;
import edu.devry.cis470.tps.repository.StaffRepository;
import edu.devry.cis470.tps.service.StaffService;
import edu.devry.cis470.tps.service.dto.UpdateStaffRequest;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffRepository staffRepository;

	@Override
	@Transactional
	public Staff createNewStaff(final String username, final String password,
			final String email) throws NonUniqueUsernameException {
		if (null != staffRepository.findByUsername(username))
			throw new NonUniqueUsernameException();

		final Staff staff = new Staff();
		staff.setUsername(username);
		staff.setPassword(password);
		staff.setEmail(email);
		return staffRepository.save(staff);
	}

	@Override
	@Transactional
	public Staff updateStaff(final Long staffId,
			final UpdateStaffRequest request) {
		final EducationLevel educationLevel = EducationLevel.valueOf(request
				.getEducationLevel());

		final Staff staff = staffRepository.findOne(staffId);
		staff.setCity(request.getCity());
		staff.setDesiredSalary(request.getDesiredSalary());
		staff.setEducationLevel(educationLevel);
		staff.setYearsExperience(request.getYearsExperience());
		return staffRepository.save(staff);
	}

	@Override
	public Staff updateStaffPicture(final Long staffId, final InputStream stream)
			throws IOException {
		final Staff staff = staffRepository.findOne(staffId);

		final byte[] pictureData = IOUtils.toByteArray(stream);
		staff.setPicture(pictureData);
		return staffRepository.save(staff);
	}

}
