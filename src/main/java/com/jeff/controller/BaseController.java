package com.jeff.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeff.model.User;
import com.jeff.services.IBaseService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class BaseController {

	private static final String VIEW_INDEX = "index";
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(BaseController.class);
	private IBaseService baseService;
	
	@Autowired
	public BaseController(@Qualifier(value = "baseService") IBaseService bs){
			this.baseService = bs;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {

		return VIEW_INDEX;

	}

	@RequestMapping(value = "/getDetails", method = RequestMethod.GET)
	public @ResponseBody String welcomeName() {

		List<User> users = null;
		try {
			users= baseService.getUserDetails();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONArray ja = new JSONArray();
		for (User user : users) {
			JSONObject jo = new JSONObject();
			jo.put("name", user.getName());
			jo.put("balance", user.getBalance());
			ja.add(jo);
		}
		return ja.toString();
	}

}