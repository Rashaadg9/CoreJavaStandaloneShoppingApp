package com.cognixia.jump.menus;

import java.sql.Timestamp;
import java.util.List;

import com.cognixia.jump.model.Inventory;
import com.cognixia.jump.model.Invoice;

public class Menus
{
	public static void InitalMenu()
	{
		System.out.println("\n+===============================================+\n");
		System.out.printf("%2s %-20s %s\n", "|", "1.REGISTER", "|");
		System.out.printf("%2s %-20s %s\n", "|", "2.LOGIN", "|");
		System.out.printf("%2s %-20s %s\n", "|", "3.EXIT", "|");
		System.out.println("\n+===============================================+\n");
	}
	
	public static void LoggedInMenu()
	{
		System.out.println("\n+===============================================+\n");
		System.out.printf("%2s %-20s %s\n", "|", "1.BUY AN ITEM", "|");
		System.out.printf("%2s %-20s %s\n", "|", "2.REPLACE AN ITEM", "|");
		System.out.printf("%2s %-20s %s\n", "|", "3.LogOut", "|");
		System.out.println("\n+===============================================+\n");
	}
	
	public static void ListItemsMenu(List<Inventory> listAllItems)
	{
		System.out.println("\n+=======================================================================================+\n");
		System.out.printf("%2s %-20s %-20s %-20s %-21s %s\n", "|", "Items", "Item Code", "Price", "Stock", "|");
		listAllItems.forEach( (i) -> { System.out.printf("%2s %-20s %-20s $%-20d %-20d %s\n", "|", i.getItemName(), i.getItemCode(), i.getItemPrice(), i.getItemCount(), "|"); } );
		System.out.println("\n+=======================================================================================+\n");
	}
	
	public static void ListInvoicesMenu(List<Invoice> listInvoice)
	{
		System.out.println("\n+===============================================+\n");
		System.out.printf("%2s %-20s %-21s %s\n", "|", "InvoiceNo", "Date", "|");
		listInvoice.forEach( (i) -> {System.out.printf("%2s %-20s %-20s %s\n", "|", i.getInvoiceNo(), i.getDATE(), "|"); } );
		System.out.println("\n+===============================================+\n");
	}
	
	public static void ViewInvoiceMenu(String name, Timestamp date, int InvoiceNo, List<Inventory> listAllItems)
	{
		System.out.println("\n+=======================================================================================+\n");
		System.out.println("Name: " + name + " | " + "Date: " + date);
		System.out.println("InvoiceNo: " + InvoiceNo + "\n");
		System.out.printf("%2s %-20s %-20s %-21s %s\n", "|", "Items", "Item Code", "Price", "|");
		listAllItems.forEach( (i) -> { System.out.printf("%2s %-20s %-20s $%-20d %s\n", "|", i.getItemName(), i.getItemCode(), i.getItemPrice(), "|"); } );
		System.out.println("\n+=======================================================================================+\n");
	}
	
}
