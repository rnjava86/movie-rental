package com.thoughtworks.movierental.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String name;

	@Column
	private String email;

	@OneToMany(targetEntity = Rental.class, mappedBy = "customer")
	private Rentals rentals = new Rentals();

	protected Customer() {
	}

	public Customer(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public void addRental(Rental arg) {
		arg.setCustomer(this);
		rentals.add(arg);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		
		return new TextStatement().display(name, rentals);
	}


	public String htmlStatement() {
		return new HtmlStatement().display(name, rentals);

	}


	public String getEmail() {
		return email;
	}
	
	
}
