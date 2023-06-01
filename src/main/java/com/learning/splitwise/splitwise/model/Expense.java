package com.learning.splitwise.splitwise.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Expense extends BaseModel {

	private double totalAmount;

	@ManyToOne
	private User createdBy; // One user can create many expenses

	private Date createdAt;

	private String description;

	@ManyToOne
	private Currency currency; // One currency can have many expenses

	@ManyToMany
	private List<User> participants; // One User can have many expenses, one expense can have many users

}
