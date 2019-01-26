package com.thoughtworks.movierental.model;

public class TextStatement {
	public String display(String name, Rentals rentals) {
		String result = "Rental Record for " + name + "\n";
		for (Rental rental : rentals) {
			// show figures for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t" + String.valueOf(rental.amount()) + "\n";
		}

		// add footer lines result
		result += "Amount owed is " + String.valueOf(rentals.totalAmount()) + "\n";
		result += "You earned " + String.valueOf(rentals.totalFrequentRenterPoints()) + " frequent renter points";
		return result;
	}
	
}

