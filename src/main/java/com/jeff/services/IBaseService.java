package com.jeff.services;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;

import com.jeff.model.Order;
import com.jeff.model.User;

public interface IBaseService {
	List<User> getUserDetails() throws SQLException;
	List<Order> getOrderByDate(String date) throws SQLException;
	void updateOrders(JSONArray orders) throws SQLException;
}
