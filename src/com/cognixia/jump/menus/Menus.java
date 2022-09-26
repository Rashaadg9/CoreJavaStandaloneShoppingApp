package com.cognixia.jump.menus;

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
}
