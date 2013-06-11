package edu.devry.cis470.tps.controller.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;

import edu.devry.cis470.tps.controller.WebController;
import edu.devry.cis470.tps.controller.dto.CreateContractForm;
import edu.devry.cis470.tps.controller.dto.RegisterForm;
import edu.devry.cis470.tps.controller.dto.UpdateProfileForm;
import edu.devry.cis470.tps.domain.StaffingContract;
import edu.devry.cis470.tps.service.ClientService;
import edu.devry.cis470.tps.service.EducationLevelService;
import edu.devry.cis470.tps.service.StaffService;
import edu.devry.cis470.tps.service.impl.NonUniqueUsernameException;

@Controller
public class WebControllerImpl implements WebController {

	@Autowired
	protected StaffService staffService;

	@Autowired
	protected ClientService clientService;

	@Autowired
	protected EducationLevelService educationLevelService;

	@Override
	public ModelAndView contractPage(final Long contractId) {
		final StaffingContract contract = clientService
				.getStaffingContract(contractId);
		if (null == contract)
			throw new Http404Exception();

		final ModelAndView mav = new ModelAndView("contract");
		return mav;
	}

	private ModelAndView createClient(final RegisterForm form) {
		try {
			clientService.createNewClient(form.getUserName(),
					form.getPassword(), form.getEmailAddress());
		} catch (final NonUniqueUsernameException e) {
			return homeWithRegistrationError(form, "Username already exists!");
		}
		return new ModelAndView("redirect:/manage");
	}

	@Override
	public ModelAndView createContract(final CreateContractForm form) {
		return new ModelAndView("redirect:/manage");
	}

	private ModelAndView createStaff(final RegisterForm form) {
		try {
			staffService.createNewStaff(form.getUserName(), form.getPassword(),
					form.getEmailAddress());
		} catch (final NonUniqueUsernameException e) {
			return homeWithRegistrationError(form, "Username already exists!");
		}
		return new ModelAndView("redirect:/profile");
	}

	@Override
	public ModelAndView createUser(final RegisterForm form) {
		if (!form.getPassword().equals(form.getPasswordConfirm()))
			return homeWithRegistrationError(form,
					"Provided passwords don't match!");

		switch (form.getUserType()) {
		case CLIENT:
			return createClient(form);
		case STAFF:
			return createStaff(form);
		}
		throw new RuntimeException("Unsupported Staff Type!!");
	}

	@Override
	public ModelAndView homePage() {
		final HashMap<String, Object> model = Maps.newHashMap();
		model.put("register", new RegisterForm());
		return new ModelAndView("home", model);
	}

	private ModelAndView homeWithRegistrationError(final RegisterForm form,
			final Object message) {
		form.setPassword(null);
		form.setPasswordConfirm(null);

		final HashMap<String, Object> model = Maps.newHashMap();
		model.put("reg.errors", message);
		model.put("register", form);
		return new ModelAndView("home", model);
	}

	@Override
	public ModelAndView managePage() {
		return new ModelAndView("manage");
	}

	@Override
	public ModelAndView profilePage() {
		return new ModelAndView("profile");
	}

	@Override
	public ModelAndView searchPage(final Integer minYearsExp,
			final Integer maxYearsExp, final Integer salary,
			final String educationLevel, final String city) {
		return new ModelAndView("search");
	}

	@Override
	public ModelAndView updatePicture(final HttpRequest request) {
		return new ModelAndView("redirect:/profile");
	}

	@Override
	public ModelAndView updateProfile(final UpdateProfileForm form) {
		return new ModelAndView("redirect:/profile");
	}

}
