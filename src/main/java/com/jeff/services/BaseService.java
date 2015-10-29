package com.jeff.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.jeff.model.Order;
import com.jeff.model.User;

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
	            	user.setUserId(result.getInt("user_id"));
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
	
	@Override
	public List<Order> getOrderByDate(String date) throws SQLException{
		SimpleDriverDataSource dataSource = null;
		
		try {
			dataSource = start();
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sqlSelect = "SELECT * FROM orders where date='" + date+"';";
			List<Order> orderList = null;
			orderList = jdbcTemplate.query(sqlSelect, new RowMapper<Order>() {
	 
	            public Order mapRow(ResultSet result, int rowNum) throws SQLException {
	            	Order order = new Order();
	            	order.setOrderId(result.getInt("order_id"));
	            	order.setAmount(result.getInt("amount"));
	            	order.setDate(result.getString("date"));
	            	order.setIsShare(result.getString("is_share"));
	            	order.setUserId(result.getInt("user_id"));
	            	order.setItem(result.getString("item"));
	                return order;
	            }
	             
	        });
			return orderList;
		} finally {
			try {
				close(dataSource);
			} catch (Exception e) {
			}
		}
	}
	
	@Override
	public void updateOrders(JSONArray orders) throws SQLException{
		SimpleDriverDataSource dataSource = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String time = sdf.format(date);
		
		try {
			dataSource = start();
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			for(Object obj : orders){
				JSONObject jo = new JSONObject(obj.toString());
				String sql = "";
				if(jo.has("orderId")){
					sql = "UPDATE orders SET user_id=" +
							jo.getInt("userId") + ", amount=" + jo.getInt("amount") + ", date='" + time +
							"', is_share='"+jo.getString("isShare")+"', item='"+jo.getString("item")+"' WHERE order_id="+jo.getInt("orderId");
				}else{
					sql = "INSERT INTO orders (user_id, amount, date, is_share, item) VALUES ("+
							jo.getInt("userId")+", "+ jo.getInt("amount")+", '"+ time+ "', '"+ jo.getString("isShare")+"', '"+ jo.getString("item")+"' )";
				}
				 
				jdbcTemplate.update(sql);
			}
		} finally {
			try {
				close(dataSource);
			} catch (Exception e) {
			}
		}
	}
}
