package com.cognixia.jump;

import java.sql.SQLException;
import java.util.Scanner;

import com.cognixia.jump.dao.UserDao;
import com.cognixia.jump.menus.Menus;
import com.cognixia.jump.model.LoginForm;
import com.cognixia.jump.model.User;

public class App
{
	static Scanner sc = new Scanner(System.in);
	static UserDao userDao = new UserDao();
	static int UserId = -1;
	
	public static void main(String[] args)
	{
		int choice = 0;
		boolean running = true;
		
		while(running)
		{
			Menus.InitalMenu();
			System.out.print("Choice: ");
			choice = sc.nextInt();
			
			switch (choice)
			{
				case 1:
					//
					break;
				case 2:
					login();
					if (UserId > 0)
						mainMenu();
					break;
				case 3:
				try {
					UserDao.conn.close();
					System.out.println("Connection was closed");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					running = false;
					break;
				
				default:
					System.out.println("Invalid choice " + choice);
			}
		}
	
	}
	
	public static void login()
	{
		sc.nextLine();
		LoginForm lf = new LoginForm();
		
		System.out.print("Username: ");
		lf.UserName = sc.nextLine();
		System.out.print("Password: ");
		lf.Password = sc.nextLine();
		User user = userDao.getUser(lf);
		
		if(user == null)
		{
			System.out.println("Invalid Username/Password Combination");
		}
		else
			UserId = user.getUserId();
		
		System.out.println(UserId);
	}
	
	public static void mainMenu()
	{
		int choice = 0;
		User user = userDao.getUser(UserId);
		System.out.println("Welcome " + user.getName());
		
		while(UserId > 0)
		{
			Menus.LoggedInMenu();
			System.out.print("Choice: ");
			choice = sc.nextInt();
			
			switch (choice)
			{
				case 1:
					//
					break;
				case 2:
					//
					break;
				case 3:
					UserId = -1;
					break;
				default:
					System.out.println("Invalid choice " + choice);
			}
		}
	}

}
