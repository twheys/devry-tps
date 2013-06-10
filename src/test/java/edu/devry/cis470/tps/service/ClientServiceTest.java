package edu.devry.cis470.tps.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.common.collect.Lists;

import edu.devry.cis470.tps.domain.Client;
import edu.devry.cis470.tps.domain.Staff;
import edu.devry.cis470.tps.domain.StaffingContract;
import edu.devry.cis470.tps.service.dto.StaffingContractRequest;
import edu.devry.cis470.tps.service.impl.NonUniqueUsernameException;
import edu.devry.cis470.tps.test.AbstractTest;

public class ClientServiceTest extends AbstractTest {

	@Test
	public void testCreateNewClient() throws NonUniqueUsernameException {
		final String username = "test01";
		final String password = "123456";
		final Client client = clientService.createNewClient(username, password);

		assertNotNull(client.getId());
		assertEquals(username, client.getUsername());
		assertEquals(password, client.getPassword());
	}

	@Test
	public void testCreateStaffingContract() throws NonUniqueUsernameException {
		final Staff staff1 = staffService.createNewStaff("test01", "123");
		final Staff staff2 = staffService.createNewStaff("test02", "123");
		final Staff staff3 = staffService.createNewStaff("test03", "123");
		final StaffingContractRequest request = new StaffingContractRequest();
		request.setCity("Washington, DC");
		request.setDesiredSalary(45000L);
		request.setStaffIds(Lists.newArrayList(staff1.getId(), staff2.getId(),
				staff3.getId()));

		final StaffingContract contract = clientService
				.createStaffingContract(request);

		assertNotNull(contract.getId());
		assertEquals("Washington, DC", contract.getLocation());
		assertEquals(new Long(45000L), contract.getDesiredSalary());
		assertTrue(contract.getDesiredStaff().contains(staff1));
		assertTrue(contract.getDesiredStaff().contains(staff2));
		assertTrue(contract.getDesiredStaff().contains(staff3));
	}

	@Test
	public void testGetStaffingContract() throws NonUniqueUsernameException {
		final Staff staff1 = staffService.createNewStaff("test01", "123");
		final Staff staff2 = staffService.createNewStaff("test02", "123");
		final Staff staff3 = staffService.createNewStaff("test03", "123");
		final StaffingContractRequest request = new StaffingContractRequest();
		request.setCity("Washington, DC");
		request.setDesiredSalary(45000L);
		request.setStaffIds(Lists.newArrayList(staff1.getId(), staff2.getId(),
				staff3.getId()));

		final StaffingContract contract = clientService
				.createStaffingContract(request);

		final StaffingContract result = clientService
				.getStaffingContract(contract.getId());

		assertNotNull(result.getId());
		assertEquals("Washington, DC", result.getLocation());
		assertEquals(new Long(45000L), result.getDesiredSalary());
		assertTrue(result.getDesiredStaff().contains(staff1));
		assertTrue(result.getDesiredStaff().contains(staff2));
		assertTrue(result.getDesiredStaff().contains(staff3));
	}

	@Test(expected = NonUniqueUsernameException.class)
	public void testUniqueUsernameConstraint()
			throws NonUniqueUsernameException {
		staffService.createNewStaff("test01", "123");
		staffService.createNewStaff("test01", "123");
	}
}