package edu.devry.cis470.tps.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import edu.devry.cis470.tps.domain.Client;
import edu.devry.cis470.tps.domain.Staff;
import edu.devry.cis470.tps.repository.ClientRepository;
import edu.devry.cis470.tps.repository.StaffRepository;

@Service("TPSUserService")
public class TPSUserService implements UserDetailsService {

	private static final Logger LOG = LoggerFactory
			.getLogger(TPSUserService.class);

	public static User createClientAuthentication(final Client client) {
		return new User(client.getUserName(), client.getPassword(), true, true,
				true, true, Lists.newArrayList(new SimpleGrantedAuthority(
						"ROLE_CLIENT")));
	}

	public static User createStaffAuthentication(final Staff staff) {
		return new User(staff.getUserName(), staff.getPassword(), true, true,
				true, true, Lists.newArrayList(new SimpleGrantedAuthority(
						"ROLE_STAFF")));
	}

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private StaffRepository staffRepository;

	private UserDetails loadClientByUsername(final String username) {
		final Client client = clientRepository.findByUserName(username);
		if (null == client)
			return null;

		return createClientAuthentication(client);
	}

	private UserDetails loadStaffByUsername(final String username) {
		final Staff staff = staffRepository.findByUserName(username);
		if (null == staff)
			return null;

		return createStaffAuthentication(staff);
	}

	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {
		LOG.info("Attempting to log in user {}", username);
		final UserDetails staffDetails = loadStaffByUsername(username);
		if (null != staffDetails)
			return staffDetails;
		final UserDetails clientDetails = loadClientByUsername(username);
		if (null != clientDetails)
			return clientDetails;
		LOG.info("No user found with username: {}", username);
		throw new UsernameNotFoundException(username);
	}

}
