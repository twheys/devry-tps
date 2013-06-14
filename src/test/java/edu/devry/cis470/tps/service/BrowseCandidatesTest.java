package edu.devry.cis470.tps.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import edu.devry.cis470.tps.domain.EducationLevel;
import edu.devry.cis470.tps.domain.Staff;
import edu.devry.cis470.tps.service.dto.BrowseRequest;
import edu.devry.cis470.tps.service.impl.NonUniqueUsernameException;
import edu.devry.cis470.tps.test.AbstractTest;

public class BrowseCandidatesTest extends AbstractTest {

	private static final String WASHINGTON_DC = "Washington, DC";
	private static final String MIAMI = "Miami, FL";
	private static final String SAN_FRANCISCO = "San Francisco, CA";

	@Test
	public void testBrowseCandidates() throws NonUniqueUsernameException {
		final Staff staff1 = createSearchableStaff("browse01", SAN_FRANCISCO,
				EducationLevel.BACHELORS, 50000, 5);
		final Staff staff2 = createSearchableStaff("browse02", MIAMI,
				EducationLevel.MASTERS, 40000, 3);
		final Staff staff3 = createSearchableStaff("browse03", SAN_FRANCISCO,
				EducationLevel.HIGH_SCHOOL, 55000, 8);
		final Staff staff4 = createSearchableStaff("browse04", MIAMI,
				EducationLevel.BACHELORS, 50000, 5);
		final Staff staff5 = createSearchableStaff("browse05", SAN_FRANCISCO,
				EducationLevel.HIGH_SCHOOL, 55000, 11);

		final BrowseRequest request = new BrowseRequest();
		request.setCity(SAN_FRANCISCO);
		request.setMaximumSalary(50000L);
		request.setMinYearsExperience(5);
		request.setMaxYearsExperience(10);
		request.setEducationLevel(EducationLevel.BACHELORS.toString());
		final List<Staff> result = clientService.browseCandidates(request);
		assertEquals(1, result.size());
		assertTrue(result.contains(staff1));
		assertFalse(result.contains(staff2));
		assertFalse(result.contains(staff3));
		assertFalse(result.contains(staff4));
		assertFalse(result.contains(staff5));
	}

	@Test
	public void testBrowseCandidates_City() throws NonUniqueUsernameException {
		final Staff staff1 = createSearchableStaff("browse04", MIAMI,
				EducationLevel.BACHELORS, 45000, 3);
		final Staff staff2 = createSearchableStaff("browse05", MIAMI,
				EducationLevel.BACHELORS, 45000, 3);
		final Staff staff3 = createSearchableStaff("browse06", WASHINGTON_DC,
				EducationLevel.BACHELORS, 45000, 3);

		final BrowseRequest request = new BrowseRequest();
		request.setCity(MIAMI);
		final List<Staff> result = clientService.browseCandidates(request);
		assertEquals(2, result.size());
		assertTrue(result.contains(staff1));
		assertTrue(result.contains(staff2));
		assertFalse(result.contains(staff3));
	}

	@Test
	public void testBrowseCandidates_EducationLevel()
			throws NonUniqueUsernameException {
		final Staff staff1 = createSearchableStaff("browse07", WASHINGTON_DC,
				EducationLevel.BACHELORS, 45000, 3);
		final Staff staff2 = createSearchableStaff("browse08", WASHINGTON_DC,
				EducationLevel.MASTERS, 45000, 3);
		final Staff staff3 = createSearchableStaff("browse09", WASHINGTON_DC,
				EducationLevel.ASSOCIATES, 45000, 3);

		final BrowseRequest request = new BrowseRequest();
		request.setEducationLevel(EducationLevel.BACHELORS.toString());
		final List<Staff> result = clientService.browseCandidates(request);
		assertEquals(2, result.size());
		assertTrue(result.contains(staff1));
		assertTrue(result.contains(staff2));
		assertFalse(result.contains(staff3));
	}

	@Test
	public void testBrowseCandidates_MaxExp() throws NonUniqueUsernameException {
		final Staff staff1 = createSearchableStaff("browse10", WASHINGTON_DC,
				EducationLevel.BACHELORS, 45000, 2);
		final Staff staff2 = createSearchableStaff("browse11", WASHINGTON_DC,
				EducationLevel.MASTERS, 45000, 6);
		final Staff staff3 = createSearchableStaff("browse12", WASHINGTON_DC,
				EducationLevel.ASSOCIATES, 45000, 7);

		final BrowseRequest request = new BrowseRequest();
		request.setMaxYearsExperience(6);
		final List<Staff> result = clientService.browseCandidates(request);
		assertEquals(2, result.size());
		assertTrue(result.contains(staff1));
		assertTrue(result.contains(staff2));
		assertFalse(result.contains(staff3));
	}

	@Test
	public void testBrowseCandidates_MinExp() throws NonUniqueUsernameException {
		final Staff staff1 = createSearchableStaff("browse13", WASHINGTON_DC,
				EducationLevel.BACHELORS, 45000, 2);
		final Staff staff2 = createSearchableStaff("browse14", WASHINGTON_DC,
				EducationLevel.MASTERS, 45000, 7);
		final Staff staff3 = createSearchableStaff("browse15", WASHINGTON_DC,
				EducationLevel.ASSOCIATES, 45000, 1);

		final BrowseRequest request = new BrowseRequest();
		request.setMinYearsExperience(2);
		final List<Staff> result = clientService.browseCandidates(request);
		assertEquals(2, result.size());
		assertTrue(result.contains(staff1));
		assertTrue(result.contains(staff2));
		assertFalse(result.contains(staff3));
	}

	@Test
	public void testBrowseCandidates_Salary() throws NonUniqueUsernameException {
		final Staff staff1 = createSearchableStaff("browse16", MIAMI,
				EducationLevel.BACHELORS, 25000, 3);
		final Staff staff2 = createSearchableStaff("browse17", MIAMI,
				EducationLevel.BACHELORS, 45000, 3);
		final Staff staff3 = createSearchableStaff("browse18", MIAMI,
				EducationLevel.BACHELORS, 55000, 3);

		final BrowseRequest request = new BrowseRequest();
		request.setMaximumSalary(50000L);
		final List<Staff> result = clientService.browseCandidates(request);
		assertEquals(2, result.size());
		assertTrue(result.contains(staff1));
		assertTrue(result.contains(staff2));
		assertFalse(result.contains(staff3));
	}
}
