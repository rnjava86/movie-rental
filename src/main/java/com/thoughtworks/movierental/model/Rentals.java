package com.thoughtworks.movierental.model;

import java.util.ArrayList;

public class Rentals extends ArrayList<Rental> {

	double totalAmount() {
		double totalAmount = 0;
		for (Rental rental : this) {
			totalAmount += rental.amount();
		}
		return totalAmount;
	}

	int totalFrequentRenterPoints() {
		int totalFrequentRenterPoints = 0;
		for (Rental rental : this) {
			totalFrequentRenterPoints += rental.frequentRenterPoints();
		}
		return totalFrequentRenterPoints;
	}
	

}
