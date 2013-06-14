package edu.devry.cis470.tps.service.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.devry.cis470.tps.domain.EducationLevel;
import edu.devry.cis470.tps.domain.Staff;
import edu.devry.cis470.tps.repository.StaffRepository;
import edu.devry.cis470.tps.service.StaffService;
import edu.devry.cis470.tps.service.dto.UpdateProfileRequest;

@Service
public class StaffServiceImpl implements StaffService {

	private static final Logger LOG = LoggerFactory
			.getLogger(StaffService.class);

	@Autowired
	private StaffRepository staffRepository;

	@Override
	@Transactional
	public Staff createNewStaff(final String userName, final String password,
			final String email) throws NonUniqueUsernameException {
		if (null != staffRepository.findByUserName(userName))
			throw new NonUniqueUsernameException();

		final Staff staff = new Staff();
		staff.setUserName(userName);
		staff.setPassword(password);
		staff.setEmail(email);
		return staffRepository.save(staff);
	}

	@Override
	public Staff getStaff(final String userName) throws NotFoundException {
		LOG.info("Locating staff by user name: {}", userName);
		final Staff staff = staffRepository.findByUserName(userName);
		if (null == staff)
			throw new NotFoundException(userName);

		return staff;
	}

	@Override
	@Transactional
	public Staff updateStaff(final String userName,
			final UpdateProfileRequest request) {
		final EducationLevel educationLevel = EducationLevel.valueOf(request
				.getEducationLevel());

		final Staff staff = staffRepository.findByUserName(userName);
		staff.setFirstName(request.getFirstName());
		staff.setLastName(request.getLastName());
		staff.setCity(request.getCity());
		staff.setDesiredSalary(request.getDesiredSalary());
		staff.setEducationLevel(educationLevel);
		staff.setYearsExperience(request.getYearsExperience());
		return staffRepository.save(staff);
	}

	@Override
	public Staff updateStaffPicture(final String userName,
			final InputStream stream) throws IOException {
		final Staff staff = staffRepository.findByUserName(userName);

		final byte[] pictureData = IOUtils.toByteArray(stream);
		staff.setPicture(pictureData);
		return staffRepository.save(staff);
	}

}
