package com.learning.splitwise.splitwise.dto;

import com.learning.splitwise.splitwise.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
	private User from;
	private User to;
	private double amount;

	public Transaction() {
	}

	public Transaction(User from, User to, double amount) {
		this.from = from;
		this.to = to;
		this.amount = amount;
	}
}
