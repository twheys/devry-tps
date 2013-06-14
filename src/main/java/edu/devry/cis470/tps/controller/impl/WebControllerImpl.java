package edu.devry.cis470.tps.controller.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;

import edu.devry.cis470.tps.controller.WebController;
import edu.devry.cis470.tps.controller.dto.CreateContractForm;
import edu.devry.cis470.tps.controller.dto.RegisterForm;
import edu.devry.cis470.tps.controller.dto.SearchForm;
import edu.devry.cis470.tps.controller.dto.UpdatePictureForm;
import edu.devry.cis470.tps.controller.dto.UpdateProfileForm;
import edu.devry.cis470.tps.domain.Client;
import edu.devry.cis470.tps.domain.Staff;
import edu.devry.cis470.tps.domain.StaffingContract;
import edu.devry.cis470.tps.security.TPSUserService;
import edu.devry.cis470.tps.service.ClientService;
import edu.devry.cis470.tps.service.EducationLevelService;
import edu.devry.cis470.tps.service.StaffService;
import edu.devry.cis470.tps.service.dto.BrowseRequest;
import edu.devry.cis470.tps.service.dto.StaffingContractRequest;
import edu.devry.cis470.tps.service.dto.UpdateProfileRequest;
import edu.devry.cis470.tps.service.impl.NonUniqueUsernameException;
import edu.devry.cis470.tps.service.impl.NotFoundException;

@Controller
public class WebControllerImpl implements WebController {

	private static final String USER_NAME_OR_PASSWORD_IS_INCORRECT = "User name or password is incorrect.";
	private static final String INVALID_FILE = "File uploaded is not a valid file.";

	private final byte[] DEFAULT_PICTURE;

	private static final Logger LOG = LoggerFactory
			.getLogger(WebController.class);

	@Autowired
	protected ClientService clientService;

	@Autowired
	protected EducationLevelService educationLevelService;

	@Autowired
	protected StaffService staffService;

	public WebControllerImpl() throws IOException {
		DEFAULT_PICTURE = IOUtils.toByteArray(WebControllerImpl.class
				.getResourceAsStream("/defaultPic.png"));
	}

	public void authNewUser(final User user) {
		final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
				user, user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	@Override
	public ModelAndView contractPage(final Long contractId) {
		final StaffingContract contract = clientService
				.getStaffingContract(contractId);
		if (null == contract)
			throw new Http404Exception();

		final ModelAndView mav = new ModelAndView("contract");
		return mav;
	}

	private BrowseRequest copy(final SearchForm form) {
		final BrowseRequest request = new BrowseRequest();
		request.setMinYearsExperience(form.getMinYearsExp());
		request.setMaxYearsExperience(form.getMaxYearsExp());
		request.setMaximumSalary(form.getSalary());
		request.setEducationLevel(form.getEducationLevel());
		request.setCity(form.getCity());
		return request;
	}

	private UpdateProfileRequest copy(final UpdateProfileForm form) {
		final UpdateProfileRequest request = new UpdateProfileRequest();
		request.setFirstName(form.getFirstName());
		request.setLastName(form.getLastName());
		request.setCity(form.getCity());
		request.setDesiredSalary(form.getDesiredSalary());
		request.setEducationLevel(form.getEducationLevel());
		request.setYearsExperience(form.getYearsExperience());
		return request;
	}

	private ModelAndView createClient(final RegisterForm form) {
		try {
			final Client client = clientService.createNewClient(
					form.getUserName(), form.getPassword(),
					form.getEmailAddress());

			LOG.info("Client successfully registered and authenticated: {}",
					client.getUserName());
			// Log user in
			authNewUser(TPSUserService.createClientAuthentication(client));
		} catch (final NonUniqueUsernameException e) {
			return homeWithRegistrationError(form, "Username already exists!");
		}
		return new ModelAndView("redirect:/manage");
	}

	@Override
	public ModelAndView createContract(final CreateContractForm form) {
		try {
			final StaffingContractRequest request = new StaffingContractRequest();
			request.setCity(form.getLocation());
			request.setDesiredSalary(form.getDesiredSalary());
			request.setStaffIds(form.getCandidates());
			clientService
					.createStaffingContract(getLoggedInUserName(), request);
			return new ModelAndView("redirect:/manage");
		} catch (final NotFoundException e) {
			LOG.error("Unabled to find client for creating a new contract.", e);
			return new ModelAndView("redirect:/");
		}
	}

	private ModelAndView createStaff(final RegisterForm form) {
		try {
			final Staff staff = staffService.createNewStaff(form.getUserName(),
					form.getPassword(), form.getEmailAddress());
			// Log user in
			LOG.info("Staff successfully registered and authenticated: {}",
					staff.getUserName());
			authNewUser(TPSUserService.createStaffAuthentication(staff));
		} catch (final NonUniqueUsernameException e) {
			return homeWithRegistrationError(form, "Username already exists!");
		}
		return new ModelAndView("redirect:/profile");
	}

	@Override
	public ModelAndView createUser(final RegisterForm form) {
		LOG.info("Attempting to register new user: {}", form);
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

	public String getLoggedInUserName() {
		final Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		return ((User) principal).getUsername();
	}

	@Override
	public ModelAndView homePage() {
		final HashMap<String, Object> model = Maps.newHashMap();
		model.put("register", new RegisterForm());
		return new ModelAndView("home", model);
	}

	@Override
	public ModelAndView homePageWithError() {
		final ModelAndView mav = homePage();
		mav.getModel().put("login.error", USER_NAME_OR_PASSWORD_IS_INCORRECT);
		return mav;
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

	private boolean isValidFile(final CommonsMultipartFile file) {
		return null != file;
	}

	@Override
	public ModelAndView managePage() {
		final String userName = getLoggedInUserName();
		List<StaffingContract> contracts;
		try {
			contracts = clientService.getAllStaffingContract(userName);

			final HashMap<String, Object> model = Maps.newHashMap();
			model.put("contracts", contracts);
			model.put("search", new SearchForm());
			return new ModelAndView("manage", model);
		} catch (final NotFoundException e) {
			LOG.error("Unabled to find client for manage page.", e);
			return new ModelAndView("redirect:/");
		}
	}

	@Override
	public ModelAndView profilePage() {
		try {
			final Staff staff = staffService.getStaff(getLoggedInUserName());
			final UpdateProfileForm form = new UpdateProfileForm(staff);
			final HashMap<String, Object> model = Maps.newHashMap();
			model.put("profile", form);
			model.put("picture", new UpdatePictureForm());
			model.put("userName", staff.getUserName());
			return new ModelAndView("profile", model);
		} catch (final NotFoundException e) {
			LOG.error("Unabled to find staff for profile page.", e);
			return new ModelAndView("redirect:/");
		}
	}

	@Override
	public ModelAndView searchPage(final SearchForm form) {
		final List<Staff> results = clientService.browseCandidates(copy(form));

		final HashMap<String, Object> model = Maps.newHashMap();
		model.put("results", results);
		model.put("search", form);
		model.put("contract", new CreateContractForm(form));
		return new ModelAndView("search", model);
	}

	@Override
	public ModelAndView updatePicture(final UpdatePictureForm form) {
		final CommonsMultipartFile file = form.getFileData();
		if (!isValidFile(file)) {
			LOG.info("Failed to upload picture");
			final HashMap<String, Object> model = Maps.newHashMap();
			model.put("reg.errors", INVALID_FILE);
			return new ModelAndView("redirect:/profile");
		}

		LOG.info("Attempting to upload a new picture: {}",
				file.getOriginalFilename());

		final String userName = getLoggedInUserName();
		LOG.info("Uploading new picture for staff: {}", userName);
		try {
			staffService.updateStaffPicture(userName, file.getInputStream());
			return new ModelAndView("redirect:/profile");
		} catch (final IOException e) {
			throw new Http500Exception();
		}
	}

	@Override
	public ModelAndView updateProfile(final UpdateProfileForm form) {
		final String userName = getLoggedInUserName();
		staffService.updateStaff(userName, copy(form));
		return new ModelAndView("redirect:/profile");
	}

	@Override
	public byte[] viewPicture(@PathVariable final String staffUserName) {
		LOG.info("Viewing picture for staff: {}", staffUserName);
		try {
			final Staff staff = staffService.getStaff(staffUserName);
			final byte[] pictureData = staff.getPicture();
			if (null == pictureData)
				return DEFAULT_PICTURE;
			else
				return pictureData;
		} catch (final NotFoundException e) {
			throw new Http404Exception();
		}
	}

}
