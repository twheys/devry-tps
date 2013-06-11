package edu.devry.cis470.tps.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import edu.devry.cis470.tps.domain.EducationLevel;
import edu.devry.cis470.tps.test.AbstractTest;

public class EducationLevelComparatorTest extends AbstractTest {

	@Test
	public void testGetEducationLevelsForMinimum1() {
		final List<EducationLevel> levels = educationLevelService
				.getEducationLevelsForMinimum(EducationLevel.HIGH_SCHOOL
						.toString());

		assertEquals(5, levels.size());
		assertTrue(levels.contains(EducationLevel.HIGH_SCHOOL));
		assertTrue(levels.contains(EducationLevel.ASSOCIATES));
		assertTrue(levels.contains(EducationLevel.BACHELORS));
		assertTrue(levels.contains(EducationLevel.MASTERS));
		assertTrue(levels.contains(EducationLevel.DOCTORATES));
	}

	@Test
	public void testGetEducationLevelsForMinimum2() {
		final List<EducationLevel> levels = educationLevelService
				.getEducationLevelsForMinimum(EducationLevel.DOCTORATES
						.toString());

		assertEquals(1, levels.size());
		assertTrue(levels.contains(EducationLevel.DOCTORATES));
	}

	@Test
	public void testGetEducationLevelsForMinimum3() {
		final List<EducationLevel> levels = educationLevelService
				.getEducationLevelsForMinimum(EducationLevel.BACHELORS
						.toString());

		assertEquals(3, levels.size());
		assertTrue(levels.contains(EducationLevel.BACHELORS));
		assertTrue(levels.contains(EducationLevel.MASTERS));
		assertTrue(levels.contains(EducationLevel.DOCTORATES));
	}
}
