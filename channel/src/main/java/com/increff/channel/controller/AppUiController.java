package com.increff.channel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/ui")
public class AppUiController extends AbstractUiController {



	@RequestMapping(value = "/channel")
	public ModelAndView channelCreate() {
		return mav("channel.html");
	}

	@RequestMapping(value = "/channellisting")
	public ModelAndView channelListing() {
		return mav("channellisting.html");
	}

	@RequestMapping(value = "/addchannelorder")
	public ModelAndView channelOrder() {
		return mav("addchannelorder.html");
	}

	@RequestMapping(value = "/managechannelorder")
	public ModelAndView managechannelOrder() {
		return mav("managechannelorder.html");
	}
}
