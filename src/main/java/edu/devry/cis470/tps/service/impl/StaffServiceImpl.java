package edu.devry.cis470.tps.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public Staff createNewStaff(final String username, final String password)
			throws NonUniqueUsernameException {
		if (null != staffRepository.findByUsername(username))
			throw new NonUniqueUsernameException();

		final Staff staff = new Staff();
		staff.setUsername(username);
		staff.setPassword(password);
		return staffRepository.save(staff);
	}

	@Override
	@Transactional
	public Staff updateStaff(final UpdateStaffRequest request) {
		final Staff staff = staffRepository.findOne(request.getStaffId());
		staff.setCity(request.getCity());
		staff.setDesiredSalary(request.getDesiredSalary());
		staff.setEducationLevel(request.getEducationLevel());
		staff.setYearsExperience(request.getYearsExperience());
		staff.setPicture(request.getPictureData());
		return staffRepository.save(staff);
	}

}
