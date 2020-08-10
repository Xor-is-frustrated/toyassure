package com.increff.channel.controller;

import com.increff.commons.data.InfoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;


@Controller
public abstract class AbstractUiController {


	@Value("${app.baseUrl}")
	private String baseUrl;

	protected ModelAndView mav(String page) {

		// Set info
		ModelAndView mav = new ModelAndView(page);

		mav.addObject("baseUrl", baseUrl);
		return mav;
	}

}
