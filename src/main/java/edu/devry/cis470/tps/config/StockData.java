package edu.devry.cis470.tps.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import edu.devry.cis470.tps.domain.EducationLevel;
import edu.devry.cis470.tps.service.StaffService;
import edu.devry.cis470.tps.service.dto.UpdateProfileRequest;

public class StockData {
	@Autowired
	protected StaffService staffService;

	private void createNewStaff(final int num, final String firstName,
			final String lastName, final int exp, final int desiredSalary,
			final EducationLevel edu, final String city) throws Exception {
		final String userName = "test_staff" + num;
		staffService.createNewStaff(userName, "test", userName + "test.com");
		final UpdateProfileRequest request = new UpdateProfileRequest();
		request.setFirstName(firstName);
		request.setLastName(lastName);
		request.setYearsExperience(exp);
		request.setDesiredSalary(desiredSalary);
		request.setEducationLevel(edu.toString());
		request.setCity(city);
		staffService.updateStaff(userName, request);
		staffService.updateStaffPicture(userName, this.getClass()
				.getResourceAsStream("/staff" + num + ".jpg"), "image/jpg");
	}

	@PostConstruct
	@Transactional
	public void populateData() throws Exception {
		int i = 1;
		createNewStaff(i++, "Joe", "Brown", 3, 43000, EducationLevel.BACHELORS,
				"Alpena");
		createNewStaff(i++, "Kelly", "Hunt", 2, 37000, EducationLevel.MASTERS,
				"Gaylord");
		createNewStaff(i++, "Paris", "Hilton", 14, 103000,
				EducationLevel.DOCTORATES, "Harbor Springs");
		createNewStaff(i++, "Lindsey", "Lohan", 5, 52000,
				EducationLevel.HIGH_SCHOOL, "Petosky");
		createNewStaff(i++, "Josh", "Grayson", 6, 44500,
				EducationLevel.BACHELORS, "Wolverine");
		createNewStaff(i++, "Michael", "Garett", 3, 52000,
				EducationLevel.MASTERS, "Wolverine");
		createNewStaff(i++, "Kevin", "Michaels", 5, 42000,
				EducationLevel.HIGH_SCHOOL, "Wolverine");
		createNewStaff(i++, "Jennifer", "Read", 5, 64000,
				EducationLevel.DOCTORATES, "Wolverine");
		createNewStaff(i++, "Keith", "Charles", 7, 72000,
				EducationLevel.MASTERS, "Petosky");
		createNewStaff(i++, "Kara", "DeVry", 4, 47000,
				EducationLevel.HIGH_SCHOOL, "Harbor Springs");
		createNewStaff(i++, "Gabriel", "James", 8, 92000,
				EducationLevel.ASSOCIATES, "Gaylord");
		createNewStaff(i++, "Paris", "Hilton", 14, 103000,
				EducationLevel.ASSOCIATES, "Alpena");
		createNewStaff(i++, "Bruno", "Mars", 1, 34000,
				EducationLevel.BACHELORS, "Gaylord");
		createNewStaff(i++, "Jennifer", "Lopez", 7, 53000,
				EducationLevel.BACHELORS, "Gaylord");
	}

}
