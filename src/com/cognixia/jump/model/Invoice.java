package com.cognixia.jump.model;

import java.sql.Timestamp;

public class Invoice
{
	public int InvoiceNo;
	public int UserId;
	public Timestamp DATE;
	public String Items;
	
	public Invoice()
	{}
	
	public Invoice(int invoiceNo, int userId, Timestamp dATE, String items) {
		super();
		InvoiceNo = invoiceNo;
		UserId = userId;
		DATE = dATE;
		Items = items;
	}


	public int getInvoiceNo() {
		return InvoiceNo;
	}


	public void setInvoiceNo(int invoiceNo) {
		InvoiceNo = invoiceNo;
	}


	public int getUserId() {
		return UserId;
	}


	public void setUserId(int userId) {
		UserId = userId;
	}


	public Timestamp getDATE() {
		return DATE;
	}


	public void setDATE(Timestamp dATE) {
		DATE = dATE;
	}


	public String getItems() {
		return Items;
	}


	public void setItems(String items) {
		Items = items;
	}


	@Override
	public String toString() {
		return "Invoice [InvoiceNo=" + InvoiceNo + ", UserId=" + UserId + ", DATE=" + DATE + ", Items=" + Items + "]";
	}
	
}
