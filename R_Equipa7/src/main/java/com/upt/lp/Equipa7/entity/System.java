package com.upt.lp.Equipa7.entity;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class System {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int balance;
	
	@OneToMany(mappedBy = "system")
	private List<Category>categories;
	
	public System() {}

	public System(int balance,List<Category> categories) {
		super();
		this.balance = balance;
		this.categories = categories;
	}
	
////Getters and setters/////////// 
	public List<Category> getCategories() {
		return categories;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
}
