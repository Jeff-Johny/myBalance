package com.jeff.services;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.jeff.model.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Service("baseService")
@Repository
public class BaseService implements IBaseService{
	public BaseService(){
		
	}
	private SimpleDriverDataSource start() throws SQLException {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriver(new com.mysql.jdbc.Driver());
        dataSource.setUrl("jdbc:mysql://localhost/myBalance");
        dataSource.setUsername("root");
        dataSource.setPassword("qburst");
		return dataSource;
	}
	private void close(SimpleDriverDataSource dataSource) throws SQLException{
		dataSource.getConnection().close();
	}
	
	@Override
	public List<User> getUserDetails() throws SQLException{
		SimpleDriverDataSource dataSource = null;
		
		try {
			dataSource = start();
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sqlSelect = "SELECT * FROM users";
			List<User> listUser = null;
	        listUser = jdbcTemplate.query(sqlSelect, new RowMapper<User>() {
	 
	            public User mapRow(ResultSet result, int rowNum) throws SQLException {
	            	User user = new User();
	            	user.setName(result.getString("name"));
	            	user.setBalance(result.getInt("balance"));
	                 
	                return user;
	            }
	             
	        });
			return listUser;
		} finally {
			try {
				close(dataSource);
			} catch (Exception e) {
			}
		}
	} 
}
