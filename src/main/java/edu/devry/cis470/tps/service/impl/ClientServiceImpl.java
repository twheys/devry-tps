package edu.devry.cis470.tps.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.mysema.query.types.expr.BooleanExpression;

import edu.devry.cis470.tps.domain.Client;
import edu.devry.cis470.tps.domain.EducationLevel;
import edu.devry.cis470.tps.domain.QStaff;
import edu.devry.cis470.tps.domain.Staff;
import edu.devry.cis470.tps.domain.StaffingContract;
import edu.devry.cis470.tps.repository.ClientRepository;
import edu.devry.cis470.tps.repository.StaffRepository;
import edu.devry.cis470.tps.repository.StaffingContractRepository;
import edu.devry.cis470.tps.service.ClientService;
import edu.devry.cis470.tps.service.EducationLevelService;
import edu.devry.cis470.tps.service.UserNameService;
import edu.devry.cis470.tps.service.dto.BrowseRequest;
import edu.devry.cis470.tps.service.dto.StaffingContractRequest;

@Service
public class ClientServiceImpl implements ClientService {
	private static final Logger LOG = LoggerFactory
			.getLogger(ClientService.class);

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private StaffingContractRepository staffingContractRepository;

	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private EducationLevelService educationLevelComparator;

	@Autowired
	private UserNameService userNameService;

	private BooleanExpression addClause(final BooleanExpression query,
			final BooleanExpression and) {
		if (null == query)
			return and;
		return query.and(and);
	}

	@Override
	public List<Staff> browseCandidates(final BrowseRequest request) {

		BooleanExpression query = null;
		if (null != request.getCity()) {
			query = addClause(query, QStaff.staff.city.eq(request.getCity()));
		}
		if (null != request.getEducationLevel()) {
			final List<EducationLevel> educationLevels = educationLevelComparator
					.getEducationLevelsForMinimum(request.getEducationLevel());
			query = addClause(
					query,
					QStaff.staff.educationLevel.in(educationLevels).or(
							QStaff.staff.educationLevel.isNull()));
		}
		if (null != request.getMaximumSalary()) {
			query = addClause(query,
					QStaff.staff.desiredSalary.loe(request.getMaximumSalary())
							.or(QStaff.staff.desiredSalary.isNull()));
		}
		if (null != request.getMinYearsExperience()) {
			query = addClause(
					query,
					QStaff.staff.yearsExperience.goe(
							request.getMinYearsExperience()).or(
							QStaff.staff.yearsExperience.isNull()));
		}
		if (null != request.getMaxYearsExperience()) {
			query = addClause(
					query,
					QStaff.staff.yearsExperience.loe(
							request.getMaxYearsExperience()).or(
							QStaff.staff.yearsExperience.isNull()));
		}

		return Lists.newArrayList(staffRepository.findAll(query));
	}

	@Override
	@Transactional
	public Client createNewClient(final String userName, final String password,
			final String email) throws NonUniqueUsernameException {
		userNameService.verifyUnique(userName);

		final Client client = new Client();
		client.setUserName(userName);
		client.setPassword(password);
		client.setEmail(email);
		return clientRepository.save(client);
	}

	@Override
	public StaffingContract createStaffingContract(final String username,
			final StaffingContractRequest request) throws NotFoundException {
		final Client client = clientRepository.findByUserName(username);
		if (null == client)
			throw new NotFoundException(username);

		final StaffingContract contract = new StaffingContract();
		contract.setLocation(request.getCity());
		contract.setDesiredSalary(request.getDesiredSalary());

		final Iterable<Staff> desiredStaff = staffRepository.findAll(request
				.getStaffIds());
		contract.setDesiredStaff(Lists.newArrayList(desiredStaff));
		contract.setClient(client);

		LOG.info("Persisting new Contract: {}", contract);
		return staffingContractRepository.save(contract);
	}

	@Transactional
	@Override
	public List<StaffingContract> getAllStaffingContract(
			final String clientUserName) throws NotFoundException {
		final Client client = clientRepository.findByUserName(clientUserName);
		if (null == client)
			throw new NotFoundException(clientUserName);

		return staffingContractRepository.findByClient(client);
	}

	@Override
	public StaffingContract getStaffingContract(final Long contractId) {
		final StaffingContract contract = staffingContractRepository
				.findOne(contractId);
		return contract;
	}

}
