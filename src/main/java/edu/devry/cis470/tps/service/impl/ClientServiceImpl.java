package edu.devry.cis470.tps.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.mysema.query.types.expr.BooleanExpression;

import edu.devry.cis470.tps.domain.Client;
import edu.devry.cis470.tps.domain.QStaff;
import edu.devry.cis470.tps.domain.Staff;
import edu.devry.cis470.tps.domain.StaffingContract;
import edu.devry.cis470.tps.repository.ClientRepository;
import edu.devry.cis470.tps.repository.StaffRepository;
import edu.devry.cis470.tps.repository.StaffingContractRepository;
import edu.devry.cis470.tps.service.ClientService;
import edu.devry.cis470.tps.service.dto.BrowseRequest;
import edu.devry.cis470.tps.service.dto.StaffingContractRequest;

public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private StaffingContractRepository staffingContractRepository;

	@Autowired
	private StaffRepository staffRepository;

	private BooleanExpression addClause(final BooleanExpression query,
			final BooleanExpression and) {
		if (null == query) {
			return and;
		}
		return query.and(and);
	}

	public List<Staff> browseCandidates(final BrowseRequest request) {
		BooleanExpression query = null;
		if (null != request.getCity()) {
			query = addClause(query, QStaff.staff.city.eq(request.getCity()));
		}
		if (null != request.getEducationLevel()) {
			query = addClause(query,
					QStaff.staff.educationLevel.eq(request.getEducationLevel()));
		}
		if (null != request.getMaximumSalary()) {
			query = addClause(query,
					QStaff.staff.desiredSalary.lt(request.getMaximumSalary()));
		}
		if (null != request.getMinYearsExperience()
				&& null != request.getMaxYearsExperience()) {
			query = addClause(
					query,
					QStaff.staff.yearsExperience.gt(
							request.getMinYearsExperience()).and(
							QStaff.staff.yearsExperience.lt(request
									.getMaxYearsExperience())));
		}

		return Lists.newArrayList(staffRepository.findAll(query));
	}

	@Transactional
	public Client createNewClient(final String username, final String password) {
		final Client client = new Client();
		client.setUsername(username);
		client.setPassword(password);
		return clientRepository.save(client);
	}

	public StaffingContract createStaffingContract(
			final StaffingContractRequest request) {
		final StaffingContract contract = new StaffingContract();
		contract.setLocation(request.getCity());
		contract.setDesiredSalary(request.getDesiredSalary());

		staffRepository.findAll(request.getStaffIds());
		return staffingContractRepository.save(contract);
	}

	public List<StaffingContract> getAllStaffingContracts(final Long clientId) {
		final Client client = clientRepository.findOne(clientId);
		return staffingContractRepository.findAllByClient(client);
	}

	public StaffingContract getStaffingContract(final Long contractId) {
		return staffingContractRepository.findOne(contractId);
	}

}
