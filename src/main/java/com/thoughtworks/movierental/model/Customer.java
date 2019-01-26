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
	private List<Rental> rentals = new ArrayList<>();

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
		String result = "Rental Record for " + getName() + "\n";
		for (Rental rental : rentals) {
			// show figures for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t" + String.valueOf(rental.amount()) + "\n";
		}

		// add footer lines result
		result += "Amount owed is " + String.valueOf(totalAmount()) + "\n";
		result += "You earned " + String.valueOf(totalFrequentRenterPoints()) + " frequent renter points";
		return result;
	}

	public String htmlStatement() {
		String result = "<h1>Rental Statement for <b>" + getName() + "</b></h1><br/>";
		int totalFrequentRenterPoints = totalFrequentRenterPoints();
		for (Rental rental : rentals) {
			// show figures for this rental
			result += rental.getMovie().getTitle() + " " + String.valueOf(rental.amount()) + "<br/>";
		}

		// add footer lines result
		result += "Amount owed is <b>" + String.valueOf(totalAmount()) + "</b><br/>";
		result += "You earned <b>" + String.valueOf(totalFrequentRenterPoints) + "</b> frequent renter points";
		return result;

	}

	private double totalAmount() {
		double totalAmount = 0;
		for (Rental rental : rentals) {
			totalAmount += rental.amount();
		}
		return totalAmount;
	}

	private int totalFrequentRenterPoints() {
		int totalFrequentRenterPoints = 0;
		for (Rental rental : rentals) {
			totalFrequentRenterPoints += rental.frequentRenterPoints();
		}
		return totalFrequentRenterPoints;
	}

	public String getEmail() {
		return email;
	}
}
