package com.increff.assure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppUiController extends AbstractUiController {

	
	@RequestMapping(value = "/ui/client")
	public ModelAndView brand() {
		return mav("client.html"); 
	}
	
	@RequestMapping(value = "/ui/product")
	public ModelAndView product() {
		return mav("product.html"); 
	}
	
	@RequestMapping(value = "/ui/binsku")
	public ModelAndView binsku() {
		return mav("binsku.html"); 
	} 
	
	@RequestMapping(value = "/ui/inventory")
	public ModelAndView inventory() {
		return mav("inventory.html"); 
	}
	
	@RequestMapping(value = "/ui/channel")
	public ModelAndView channel() {
		return mav("channel.html"); 
	}
	
	@RequestMapping(value = "/ui/assureorder")
	public ModelAndView assureOrder() {
		return mav("assureorder.html"); 
	}
	
	@RequestMapping(value = "/ui/channellisting")
	public ModelAndView channelListing() {
		return mav("channellisting.html"); 
	}

}
