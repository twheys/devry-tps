package edu.devry.cis470.tps.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

@MappedSuperclass
public class User extends IdEntity {

	@NotNull
	@Length(min = 4, max = 56, message = "Your username must be within 4 and 56 characters long.")
	@Pattern(regexp = "^[a-zA-Z0-9_-]*$", message = "Your username can only contain letters, numbers, hyphens, and underscores.")
	@Column(unique = true, nullable = false)
	private String userName;

	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?!.*\\s).*$", message = "Your password must contain at least 1 letter and number.")
	@Length(min = 6, message = "Your password must be at least 6 characters long.")
	@Column(unique = false, nullable = false)
	private String password;

	@Column(unique = true, nullable = false)
	@Email(message = "Please provide a valid email address.")
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
