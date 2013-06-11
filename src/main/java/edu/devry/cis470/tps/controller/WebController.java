package edu.devry.cis470.tps.controller;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.devry.cis470.tps.controller.dto.CreateContractForm;
import edu.devry.cis470.tps.controller.dto.RegisterForm;
import edu.devry.cis470.tps.controller.dto.UpdateProfileForm;

@RequestMapping("/")
public interface WebController {

	@RequestMapping(value = "contract/{contractId}", method = RequestMethod.GET)
	public ModelAndView contractPage(@PathVariable Long contractId);

	@RequestMapping(value = "contract/create", method = RequestMethod.POST)
	public ModelAndView createContract(
			@ModelAttribute("contract") CreateContractForm form);

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public ModelAndView createUser(@ModelAttribute("register") RegisterForm form);

	@RequestMapping
	public ModelAndView homePage();

	@RequestMapping(value = "manage", method = RequestMethod.GET)
	public ModelAndView managePage();

	@RequestMapping(value = "profile", method = RequestMethod.GET)
	public ModelAndView profilePage();

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public ModelAndView searchPage(@RequestParam Integer minYearsExp,
			@RequestParam Integer maxYearsExp, @RequestParam Integer salary,
			@RequestParam String educationLevel, @RequestParam String city);

	@RequestMapping(value = "profile/picture", method = RequestMethod.POST)
	public ModelAndView updatePicture(HttpRequest request);

	@RequestMapping(value = "profile/update", method = RequestMethod.POST)
	public ModelAndView updateProfile(
			@ModelAttribute("profile") UpdateProfileForm form);

}
