package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.LoginForm;
import com.cognixia.jump.model.User;

public class UserDao
{
	public static final Connection conn = ConnectionManager.getConnection();
	
	private static String SELECT_ALL_USERS = "select * from user";
	private static String SELECT_USER_BY_ID = "select * from user where UserId = ?";
	private static String SELECT_USER_LOGIN = "select * from user where UserName = ? and Password = ?";
	
	public User getUser(LoginForm lf)
	{
		User user = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_USER_LOGIN)) {
			
			pstmt.setString(1, lf.UserName);
			pstmt.setString(2, lf.Password);
			
			ResultSet rs = pstmt.executeQuery();
			
			// if product found, if statement run, if not null returned as product
			if(rs.next())
			{
				int id = rs.getInt("UserId");
				String name = rs.getString("Name");
				int addressId = rs.getInt("AddressId");
				
				user  =(new User(id, lf.UserName, name, lf.Password, addressId) );
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public User getUser(int userId)
	{
		User user = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_USER_BY_ID)) {
			
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			
			// if product found, if statement run, if not null returned as product
			if(rs.next())
			{
				int id = rs.getInt("UserId");
				String username = rs.getString("UserName");
				String name = rs.getString("Name");
				String password = rs.getString("Password");
				int addressId = rs.getInt("AddressId");
				
				user  =(new User(id, username, name, password, addressId) );
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

}
