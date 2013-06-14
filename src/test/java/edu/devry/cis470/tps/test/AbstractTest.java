package edu.devry.cis470.tps.test;

import java.io.IOException;
import java.io.InputStream;

import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import edu.devry.cis470.tps.config.ApplicationContext;
import edu.devry.cis470.tps.domain.EducationLevel;
import edu.devry.cis470.tps.domain.Staff;
import edu.devry.cis470.tps.repository.ClientRepository;
import edu.devry.cis470.tps.repository.StaffRepository;
import edu.devry.cis470.tps.repository.StaffingContractRepository;
import edu.devry.cis470.tps.service.ClientService;
import edu.devry.cis470.tps.service.EducationLevelService;
import edu.devry.cis470.tps.service.StaffService;
import edu.devry.cis470.tps.service.impl.NonUniqueUsernameException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ApplicationContext.class })
public abstract class AbstractTest {

	@Autowired
	protected StaffService staffService;

	@Autowired
	protected ClientService clientService;

	@Autowired
	protected EducationLevelService educationLevelService;

	@Autowired
	protected ClientRepository clientRepository;

	@Autowired
	protected StaffRepository staffRepository;

	@Autowired
	protected StaffingContractRepository staffingContractRepository;

	@After
	public void cleanup() {
		staffingContractRepository.deleteAll();
		staffRepository.deleteAll();
		clientRepository.deleteAll();
	}

	protected Staff createSearchableStaff(final String username,
			final String location, final EducationLevel educationLevel,
			final Integer desiredSalary, final Integer yearsExperience)
			throws NonUniqueUsernameException {
		if (null != staffRepository.findByUserName(username))
			throw new NonUniqueUsernameException();

		final Staff staff = new Staff();
		staff.setUserName(username);
		staff.setPassword("123");
		staff.setEmail(username + "@email.com");
		staff.setCity(location);
		staff.setEducationLevel(educationLevel);
		staff.setDesiredSalary(desiredSalary);
		staff.setYearsExperience(yearsExperience);
		return staffRepository.save(staff);
	}

	protected InputStream loadPicture(final String string) throws IOException {
		return this.getClass().getResourceAsStream(string);
	}
}
