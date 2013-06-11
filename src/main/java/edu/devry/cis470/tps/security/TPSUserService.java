package edu.devry.cis470.tps.security;

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

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private StaffRepository staffRepository;

	private UserDetails loadClientByUsername(final String username) {
		final Client client = clientRepository.findByUsername(username);
		if (null == client)
			return null;

		return new User(client.getUsername(), client.getPassword(), true, true,
				true, true, Lists.newArrayList(new SimpleGrantedAuthority(
						"ROLE_CLIENT")));
	}

	private UserDetails loadStaffByUsername(final String username) {
		final Staff staff = staffRepository.findByUsername(username);
		if (null == staff)
			return null;

		return new User(staff.getUsername(), staff.getPassword(), true, true,
				true, true, Lists.newArrayList(new SimpleGrantedAuthority(
						"ROLE_STAFF")));
	}

	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {
		final UserDetails staffDetails = loadStaffByUsername(username);
		if (null != staffDetails)
			return staffDetails;
		final UserDetails clientDetails = loadClientByUsername(username);
		if (null != clientDetails)
			return clientDetails;
		throw new UsernameNotFoundException(username);
	}

}
