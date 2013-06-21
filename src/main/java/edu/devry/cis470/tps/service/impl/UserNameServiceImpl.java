package edu.devry.cis470.tps.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.devry.cis470.tps.repository.ClientRepository;
import edu.devry.cis470.tps.repository.StaffRepository;
import edu.devry.cis470.tps.service.UserNameService;

@Service
public class UserNameServiceImpl implements UserNameService {
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private StaffRepository staffRepository;

	@Transactional(readOnly = true)
	@Override
	public boolean verifyUnique(final String userName)
			throws NonUniqueUsernameException {
		if (null != staffRepository.findByUserName(userName))
			throw new NonUniqueUsernameException();
		if (null != clientRepository.findByUserName(userName))
			throw new NonUniqueUsernameException();
		return true;
	}

}
