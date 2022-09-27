package com.cognixia.jump.model;

public class Inventory
{
	public String ItemCode;
	public String ItemName;
	public int ItemPrice;
	public int ItemCount;
	
	public Inventory()
	{}
	
	public Inventory(String itemCode, String itemName, int itemPrice, int itemCount)
	{
		super();
		ItemCode = itemCode;
		ItemName = itemName;
		ItemPrice = itemPrice;
		ItemCount = itemCount;
	}

	public String getItemCode() {
		return ItemCode;
	}

	public void setItemCode(String itemCode) {
		ItemCode = itemCode;
	}

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	public int getItemPrice() {
		return ItemPrice;
	}

	public void setItemPrice(int itemPrice) {
		ItemPrice = itemPrice;
	}

	public int getItemCount() {
		return ItemCount;
	}

	public void setItemCount(int itemCount) {
		ItemCount = itemCount;
	}

	@Override
	public String toString() {
		return "Inventory [ItemCode=" + ItemCode + ", ItemName=" + ItemName + ", ItemPrice=" + ItemPrice
				+ ", ItemCount=" + ItemCount + "]";
	}
	
}
