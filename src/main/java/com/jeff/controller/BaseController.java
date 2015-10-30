package com.jeff.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeff.model.Order;
import com.jeff.model.User;
import com.jeff.services.IBaseService;

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

		JSONObject ret = new JSONObject();
		List<User> users = null;
		List<Order> orders = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String time = sdf.format(date);
		try {
			users= baseService.getUserDetails();
			orders = baseService.getOrderByDate(time);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONArray userJa = new JSONArray();
		for (User user : users) {
			JSONObject jo = new JSONObject();
			jo.put("userId", user.getUserId());
			jo.put("name", user.getName());
			jo.put("balance", user.getBalance());
			userJa.put(jo);
		}
		JSONArray orderJa = new JSONArray();
		for (Order order : orders) {
			JSONObject jo = new JSONObject();
			jo.put("orderId", order.getOrderId());
			jo.put("amount", order.getAmount());
			jo.put("date", order.getDate());
			jo.put("isShare", order.getIsShare());
			jo.put("item", order.getItem());
			jo.put("userId", order.getUserId());
			jo.put("needFood", order.getNeedFood());
			jo.put("paid", order.getPaid());
			orderJa.put(jo);
		}
		ret.put("users", userJa);
		ret.put("orders", orderJa);
		return ret.toString();
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String updateOrders(@RequestBody String orders) {
		JSONArray ja = new JSONArray(orders);
		try {
			baseService.updateOrders(ja);
			return "sucess";
		} catch (SQLException e) {
			e.printStackTrace();
			return "failed";
		}
	}

}