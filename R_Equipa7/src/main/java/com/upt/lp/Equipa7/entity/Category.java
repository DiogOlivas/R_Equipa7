package com.upt.lp.Equipa7.entity;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy = "category")
	private List<Transaction>transactions;
	
	private int budget;
	
	public Category() {}

	public Category(Long id, String name, List<Transaction> transactions, int budget) {
		super();
		this.id = id;
		this.name = name;
		this.transactions = transactions;
		this.budget = budget;
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

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}
}
