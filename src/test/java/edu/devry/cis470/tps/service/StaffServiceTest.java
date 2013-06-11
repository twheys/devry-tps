package edu.devry.cis470.tps.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

import edu.devry.cis470.tps.domain.EducationLevel;
import edu.devry.cis470.tps.domain.Staff;
import edu.devry.cis470.tps.service.dto.UpdateStaffRequest;
import edu.devry.cis470.tps.service.impl.NonUniqueUsernameException;
import edu.devry.cis470.tps.test.AbstractTest;

public class StaffServiceTest extends AbstractTest {

	@Test
	public void testCreateNewStaff() throws NonUniqueUsernameException {
		final String username = "staff01";
		final String password = "123456";
		final Staff staff = staffService.createNewStaff(username, password);

		assertNotNull(staff.getId());
		assertEquals(username, staff.getUsername());
		assertEquals(password, staff.getPassword());
	}

	@Test(expected = NonUniqueUsernameException.class)
	public void testUniqueUsernameConstraint()
			throws NonUniqueUsernameException {
		staffService.createNewStaff("staff08", "123");
		staffService.createNewStaff("staff08", "123");
	}

	@Test
	public void testUpdateStaff() throws NonUniqueUsernameException,
			IOException {
		final String username = "staff01";
		final String password = "123456";
		final Staff staff = staffService.createNewStaff(username, password);

		final String city = "New York, NY";
		final Integer desiredSalary = 40000;
		final EducationLevel educationLevel = EducationLevel.BACHELORS;
		final byte[] pictureData = loadPicture("/test_pic.jpg");
		final Integer yearsExperience = 5;

		final UpdateStaffRequest request = new UpdateStaffRequest();
		request.setCity(city);
		request.setDesiredSalary(desiredSalary);
		request.setEducationLevel(educationLevel.toString());
		request.setPictureData(pictureData);
		request.setYearsExperience(yearsExperience);
		final Staff updatedStaff = staffService.updateStaff(staff.getId(),
				request);

		assertEquals(city, updatedStaff.getCity());
		assertEquals(desiredSalary, updatedStaff.getDesiredSalary());
		assertEquals(educationLevel, updatedStaff.getEducationLevel());
		assertTrue(Arrays.equals(pictureData, updatedStaff.getPicture()));
		assertEquals(yearsExperience, updatedStaff.getYearsExperience());
	}
}
