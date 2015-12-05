package com.mottledog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mottledog.bo.User;
import com.mottledog.model.ResponseBean;
import com.mottledog.service.UserService;

/**
 * @ClassName: UserController 
 * @Description: UserController
 * @author tianli 
 * @date 2015-1-21 14:46:53 
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	UserService userService;

	@Autowired
	public void setuserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = { "/", ""}, method = RequestMethod.GET)
	public String userPage(Model model) {
		logger.info("User page requested");
		return "/user/list";
	}
	
	
	@RequestMapping(value = { "/list"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseBean list(HttpServletRequest request) {
		logger.info("User page requested");
		// get a list of all users
		ResponseBean rb = new ResponseBean();
		
		int draw = ServletRequestUtils.getIntParameter(request, "draw", 1);
		rb.setDraw(draw);
		String search = ServletRequestUtils.getStringParameter(request, "search[value]","");
		
		int start = ServletRequestUtils.getIntParameter(request, "start", 1);
		int length = ServletRequestUtils.getIntParameter(request, "length", 10);
		
		if(StringUtils.isNotBlank(search)){
			//TODO
			int count = userService.countAll();
			rb.setRecordsTotal(count);
			rb.setRecordsFiltered(count);
			List<User> userList = userService.getUserListByPage(3,length);
			rb.setData(userList);
			return rb;
		}
		
		
		int count = userService.countAll();
		rb.setRecordsTotal(count);
		rb.setRecordsFiltered(count);
		List<User> userList = userService.getUserListByPage(start/length+1,length);
		rb.setData(userList);
		return rb;
	}

	@RequestMapping(value = "/cccc", method = RequestMethod.GET)
	public ModelAndView ser(Model model,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/user/ccc");
		model.addAttribute("aa", "ababb");
		return mv;
	}
	
	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createUser(Model model,HttpServletRequest request) {
		request.setAttribute("", new Object());
		return "/user/create";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createUser(@ModelAttribute("userForm") User user,
			BindingResult result) {
		// create new user
		try {
			userService.createUser(user);
		} catch (Exception e) {
			logger.error("create user error:",e);
			logger.debug("create user error:",e);
			logger.warn("create user error:",e);
			logger.info("create user error:",e);
			return "redirect:/user?bc=4";
		}
		// redirect to root
		return "redirect:/user?bc=4";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteUser(@RequestParam("userId") int userId) {
		// delete user
		userService.deleteUser(userId);
		// redirect to root
		return "redirect:/user?bc=4";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") User user,
			BindingResult result) {
		// update user
		try {
			userService.updateUser(user);
		} catch (Exception e) {
			System.out.println("Update Failed!");
			return "redirect:/user?bc=4";
		}
		// redirect to root
		return "redirect:/user?bc=4";
	}

/*	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public String getUser(Model model, @PathVariable String username) {
		// get user by name
		User user = userService.getUserByUserName(username);
		// add to session
		model.addAttribute("user", user);
		// return modify.jsp
		return "/user/modify"; 
	}*/

	@RequestMapping(value = { "/lists"}, method = RequestMethod.GET)
	@ResponseBody
	public List<User> jsonLlist(HttpServletRequest request) {
		List<User> us = userService.getUserList();
		return us;
	}
}
