package edu.devry.cis470.tps.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.devry.cis470.tps.controller.dto.CreateContractForm;
import edu.devry.cis470.tps.controller.dto.RegisterForm;
import edu.devry.cis470.tps.controller.dto.SearchForm;
import edu.devry.cis470.tps.controller.dto.UpdatePictureForm;
import edu.devry.cis470.tps.controller.dto.UpdateProfileForm;

public interface WebController {

	@RequestMapping(value = "/contract/{contractId}", method = RequestMethod.GET)
	ModelAndView contractPage(@PathVariable Long contractId);

	@RequestMapping(value = "/contract/create", method = RequestMethod.POST)
	ModelAndView createContract(
			@ModelAttribute("contract") CreateContractForm form);

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	ModelAndView createUser(@ModelAttribute("register") RegisterForm form);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	ModelAndView homePage();

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	ModelAndView homePageWithError();

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	ModelAndView managePage();

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	ModelAndView profilePage();

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	ModelAndView searchPage(@ModelAttribute("search") SearchForm form);

	@RequestMapping(value = "/profile/picture", method = RequestMethod.POST)
	ModelAndView updatePicture(UpdatePictureForm form);

	@RequestMapping(value = "/profile/update", method = RequestMethod.POST)
	ModelAndView updateProfile(@ModelAttribute("profile") UpdateProfileForm form);

	@RequestMapping(value = "/pic/{staffUserName}", method = RequestMethod.GET, produces = "image/*")
	@ResponseBody
	byte[] viewPicture(@PathVariable String staffUserName);

}
