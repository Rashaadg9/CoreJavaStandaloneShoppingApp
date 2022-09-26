package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.User;

public class UserDao
{
	public static final Connection conn = ConnectionManager.getConnection();
	
	private static String SELECT_ALL_USERS = "select * from users";
	
	public List<User> listAllUsers()
	{
		List<User> allUsers = new ArrayList<User>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_USERS);
				ResultSet rs = pstmt.executeQuery() ) {
			
			while(rs.next())
			{	
				int id = rs.getInt("user_id");
				String fname = rs.getString("first_name");
				String lname = rs.getString("last_name");
				String username = rs.getString("username");
				String password = rs.getString("pass");
				String pin = rs.getString("pin");
				Double cash = rs.getDouble("cash");
				
				//allUsers.add(new User(id, fname, lname, username, password, pin, cash));
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return allUsers;
	}

}
