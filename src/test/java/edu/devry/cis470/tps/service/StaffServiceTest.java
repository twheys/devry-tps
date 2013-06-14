package edu.devry.cis470.tps.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import edu.devry.cis470.tps.domain.EducationLevel;
import edu.devry.cis470.tps.domain.Staff;
import edu.devry.cis470.tps.service.dto.UpdateProfileRequest;
import edu.devry.cis470.tps.service.impl.NonUniqueUsernameException;
import edu.devry.cis470.tps.test.AbstractTest;

public class StaffServiceTest extends AbstractTest {

	@Test
	public void testCreateNewStaff() throws NonUniqueUsernameException {
		final String username = "staff01";
		final String password = "123456";
		final Staff staff = staffService.createNewStaff(username, password,
				"staff01@email.com");

		assertNotNull(staff.getId());
		assertEquals(username, staff.getUserName());
		assertEquals(password, staff.getPassword());
	}

	@Test(expected = NonUniqueUsernameException.class)
	public void testUniqueUsernameConstraint()
			throws NonUniqueUsernameException {
		staffService.createNewStaff("staff08", "123", "staff08@email.com");
		staffService.createNewStaff("staff08", "123", "staff08@email.com");
	}

	@Test
	public void testUpdateStaff() throws NonUniqueUsernameException,
			IOException {
		final String username = "staff01";
		final String password = "123456";
		final Staff staff = staffService.createNewStaff(username, password,
				"staff01@email.com");

		final String city = "New York, NY";
		final Integer desiredSalary = 40000;
		final EducationLevel educationLevel = EducationLevel.BACHELORS;
		final Integer yearsExperience = 5;

		final UpdateProfileRequest request = new UpdateProfileRequest();
		request.setCity(city);
		request.setDesiredSalary(desiredSalary);
		request.setEducationLevel(educationLevel.toString());
		request.setYearsExperience(yearsExperience);
		final Staff updatedStaff = staffService.updateStaff(
				staff.getUserName(), request);

		assertEquals(city, updatedStaff.getCity());
		assertEquals(desiredSalary, updatedStaff.getDesiredSalary());
		assertEquals(educationLevel, updatedStaff.getEducationLevel());
		assertEquals(yearsExperience, updatedStaff.getYearsExperience());
	}

	@Test
	public void testUpdateStaffPicture() throws NonUniqueUsernameException,
			IOException {
		final Staff staff = staffService.createNewStaff("staff01", "123456",
				"staff01@email.com");
		final byte[] pictureData = IOUtils
				.toByteArray(loadPicture("/test_pic.jpg"));

		final Staff updatedStaff = staffService.updateStaffPicture(
				staff.getUserName(), loadPicture("/test_pic.jpg"), "image/jpg");

		assertTrue(Arrays.equals(pictureData, updatedStaff.getPicture()
				.getBytes()));
	}
}
