package edu.devry.cis470.tps.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import edu.devry.cis470.tps.config.ApplicationContext;
import edu.devry.cis470.tps.repository.ClientRepository;
import edu.devry.cis470.tps.repository.StaffRepository;
import edu.devry.cis470.tps.repository.StaffingContractRepository;
import edu.devry.cis470.tps.service.ClientService;
import edu.devry.cis470.tps.service.StaffService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ApplicationContext.class })
public class AbstractTest {

	@Autowired
	protected StaffService staffService;

	@Autowired
	protected ClientService clientService;

	@Autowired
	protected ClientRepository clientRepository;

	@Autowired
	protected StaffRepository staffRepository;

	@Autowired
	protected StaffingContractRepository staffingContractRepository;
}
