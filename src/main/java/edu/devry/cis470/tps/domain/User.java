package edu.devry.cis470.tps.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class User extends IdEntity {

	@Column(unique = true, nullable = false)
	private String userName;

	@Column(unique = false, nullable = false)
	private String password;

	@Column(unique = true, nullable = false)
	private String email;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final User other = (User) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (userName == null ? 0 : userName.hashCode());
		return result;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "User [username=" + userName + ", password=<PROTECTED>, email="
				+ email + "]";
	}

}
