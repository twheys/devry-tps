package edu.devry.cis470.tps.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class StaffingContract {

	private Long desiredSalary;
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Staff> desiredStaff;
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	private String location;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final StaffingContract other = (StaffingContract) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getDesiredSalary() {
		return desiredSalary;
	}

	public List<Staff> getDesiredStaff() {
		return desiredStaff;
	}

	public Long getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id == null ? 0 : id.hashCode());
		return result;
	}

	public void setDesiredSalary(final Long desiredSalary) {
		this.desiredSalary = desiredSalary;
	}

	public void setDesiredStaff(final List<Staff> desiredStaff) {
		this.desiredStaff = desiredStaff;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setLocation(final String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "StaffingContract [id=" + id + ", location=" + location
				+ ", desiredSalary=" + desiredSalary + "]";
	}
}
