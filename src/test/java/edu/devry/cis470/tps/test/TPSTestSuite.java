package edu.devry.cis470.tps.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.devry.cis470.tps.service.BrowseCandidatesTest;
import edu.devry.cis470.tps.service.ClientServiceTest;
import edu.devry.cis470.tps.service.EducationLevelComparatorTest;
import edu.devry.cis470.tps.service.StaffServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ ClientServiceTest.class, EducationLevelComparatorTest.class,
		BrowseCandidatesTest.class, StaffServiceTest.class })
public class TPSTestSuite {

}
