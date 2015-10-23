package com.jeff.services;

import java.sql.SQLException;
import java.util.List;

import com.jeff.model.User;

public interface IBaseService {
	List<User> getUserDetails() throws SQLException;
}
