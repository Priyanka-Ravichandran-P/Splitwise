package com.learning.splitwise.splitwise.dto;

import com.learning.splitwise.splitwise.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserExpensePair {

	private User user;
	private double amount;

	public UserExpensePair(User user, double amount) {
		this.user = user;
		this.amount = amount;
	}
}
