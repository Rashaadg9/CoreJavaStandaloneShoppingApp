package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Inventory;
import com.cognixia.jump.model.Invoice;
import com.cognixia.jump.model.LoginForm;
import com.cognixia.jump.model.User;

public class UserDao
{
	public static final Connection conn = ConnectionManager.getConnection();
	
	private static String SELECT_ALL_ITEMS = "select * from Inventory";
	private static String SELECT_ITEM_BY_CODE = "select * from Inventory where ItemCode = ?";
	private static String UPDATE_ITEM_BY_CODE = "UPDATE Inventory SET ItemCount = ItemCount - 1 WHERE ItemCode = ?";
	private static String CREATE_NEW_INVOICE = "INSERT INTO Invoice VALUES(null, ?, ?, ?)";
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
	
	public List<Inventory> listAllItems()
	{
		List<Inventory> allInventory = new ArrayList<Inventory>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_ITEMS);
				ResultSet rs = pstmt.executeQuery() ) {
			
			while(rs.next())
			{	
				String ItemCode = rs.getString("ItemCode");
				String ItemName = rs.getString("ItemName");
				int ItemPrice = rs.getInt("ItemPrice");
				int ItemCount = rs.getInt("ItemCount");
				
				allInventory.add(new Inventory(ItemCode, ItemName, ItemPrice, ItemCount));
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return allInventory;
	}
	
	public Inventory getItemByCode(String ItemCode)
	{
		
		Inventory inventory = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_ITEM_BY_CODE))
			{
			
			pstmt.setString(1, ItemCode);
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next())
			{	
				String itemCode = rs.getString("ItemCode");
				String ItemName = rs.getString("ItemName");
				int ItemPrice = rs.getInt("ItemPrice");
				int ItemCount = rs.getInt("ItemCount");
				
				inventory = new Inventory(itemCode, ItemName, ItemPrice, ItemCount);
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return inventory;
	}
	
	public Boolean reduceInventory(String ItemCode, StringBuilder items)
	{
		Inventory inventory = getItemByCode(ItemCode);
		
		if(inventory.getItemCount() > 0)
		{
			try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_ITEM_BY_CODE)) {

				pstmt.setString(1, ItemCode);

				// at least one row updated
				if (pstmt.executeUpdate() > 0) {
					items.append(inventory.getItemCode() + ':' + inventory.getItemPrice() + ',');
					return true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		return false;
		}
	
	
	public Boolean createInvoice(Invoice invoice)
	{
		try (PreparedStatement pstmt = conn.prepareStatement(CREATE_NEW_INVOICE))
		{
			pstmt.setInt(1, invoice.getUserId());
			pstmt.setString(2, invoice.getDATE().toString());
			pstmt.setString(3, invoice.getItems());
		
			if (pstmt.executeUpdate() > 0)
			{
				return true;
		}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		return false;
			
			
	}

}
