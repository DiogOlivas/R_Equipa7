package com.upt.lp.Equipa7.entity;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(unique = true, nullable = false)
	private String name;
	
	private String description;
	
	private double budget;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transaction>transactions;
		
	public Category() {}

	public Category(Long id, User user, String name, String description, double budget, List<Transaction> transactions) {
		this.id = id;
		this.user = user;
		this.name = name;
		this.description = description;	
		this.budget = budget;
		this.transactions = transactions;
	}

////Getters and setters///////////
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDesc() {
		return description;
	}

	public void setDesc(String description) {
		this.description = description;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
