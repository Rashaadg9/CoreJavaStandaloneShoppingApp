package com.cognixia.jump;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cognixia.jump.dao.UserDao;
import com.cognixia.jump.menus.Menus;
import com.cognixia.jump.model.Inventory;
import com.cognixia.jump.model.Invoice;
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
		
		
		while(UserId > 0)
		{
			User user = userDao.getUser(UserId);
			System.out.println("Welcome " + user.getName() + " | " + "Balance: $" + user.getBalance() );
			Menus.LoggedInMenu();
			System.out.print("Choice: ");
			choice = sc.nextInt();
			
			switch (choice)
			{
				case 1:
					sc.nextLine();
					buyItem();
					break;
				case 2:
					sc.nextLine();
					replaceItem();
					break;
				case 3:
					UserId = -1;
					break;
				default:
					System.out.println("Invalid choice " + choice);
			}
		}
	}
	
	public static void buyItem()
	{
		String choice = "";
		StringBuilder items = new StringBuilder();
		Timestamp tS = new Timestamp(System.currentTimeMillis());
		while (choice.equals("done") == false)
		{
			Menus.ListItemsMenu(userDao.listAllItems());
			System.out.print("Enter Item Code (E to Exit): ");
			choice = sc.nextLine();
			
			switch (choice)
			{
				case "E":
				case "e":
					choice = "done";
					break;
				default:
					try
					{
						userDao.reduceInventory(UserId, choice, items);
					} catch (Exception e)
					{
						e.printStackTrace();
					}
			}
		}
		if(!items.isEmpty())
		{
			userDao.createInvoice(new Invoice(-1, UserId, tS, items.toString()));
		}
		
		
	}
	
	public static void replaceItem()
	{
		String choice = "";
		
		while (choice.equals("done") == false)
		{
			Menus.ListInvoicesMenu(userDao.ListInvoices(UserId));
			System.out.print("Enter InvoiceNo (E to Exit): ");
			choice = sc.nextLine();
			
			switch (choice)
			{
				case "E":
				case "e":
					choice = "done";
					break;
				default:
					try
					{
						Invoice invoice = userDao.getInvoice(Integer.valueOf(choice));
						String Items = invoice.getItems();
						List<Inventory> list = new ArrayList<>();
						String[] splitItems = Items.split(",");
						for(String i: splitItems)
						{
							Inventory inventory = userDao.getItem(i.replaceAll(":.*", ""));
							inventory.setItemPrice(Integer.valueOf(i.replaceAll(".*:", "")) );
							list.add(inventory);
						}
						User user = userDao.getUser(UserId);
						Menus.ViewInvoiceMenu(user.getName(), invoice.getDATE(), invoice.getInvoiceNo(),  list);
						System.out.print("Enter Items Code to refund (E to Exit): ");
						choice = sc.nextLine();
						if(choice.toLowerCase().equals("e"))
							continue;
						Timestamp day15 = new Timestamp(System.currentTimeMillis() - (long) (15 *(24 * 60 * 60* 1000)) );
						if(invoice.getDATE().after(day15))
						{
							try
							{
								userDao.updateCash(UserId, choice, list);
							}
							catch (Exception e)
							{
								e.printStackTrace();
							}
						}
						else
							System.out.println("Can not return after 15 days");
					}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}

		}
		
		
		}
}
