package com.app.spring;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	Facebook facebook;
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)

	public ModelAndView home(Locale locale) {
		logger.info("Welcome home! The client locale is {}.", locale);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping(value = "/facebook/data", method = RequestMethod.GET)
	public ModelAndView getFacebookData(){
		ModelAndView mv = new ModelAndView();
		System.out.println(facebook.isAuthorized());
		//FacebookProfile fbProfile = facebook.userOperations().getUserProfile();
		//System.out.println(fbProfile.getEmail());
		mv.setViewName("connect/receiveData");
		return mv;
	}
	
}
