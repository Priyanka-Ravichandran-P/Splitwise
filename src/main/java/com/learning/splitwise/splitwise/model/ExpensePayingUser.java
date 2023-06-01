package com.learning.splitwise.splitwise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ExpensePayingUser extends User {
	@ManyToOne
	private User user;

	@ManyToOne
	private Expense expense;

	private double amount;
}
