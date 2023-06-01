package com.learning.splitwise.splitwise.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "groups")
public class Group extends BaseModel{
	
	private String name;
	private String description;
	
	@ManyToMany
	private List<User> members;
	
	@ManyToMany
	private List<User> admins;
		
	@OneToMany
	private List<Expense> expenses;
	
	@ManyToOne
	private User createdBy; 
}
