package com.thoughtworks.movierental.model;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Rental {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name="DAYS_RENTED")
  private int daysRented;

  @Column(name="START_DATE")
  private Date startDate;

  @JoinColumn(name="MOVIE_ID")
  private Movie movie;

  @ManyToOne
  private Customer customer;

  protected Rental(){}

  public Rental(Movie movie, int daysRented) {
    this(movie, daysRented, Date.valueOf(LocalDate.now()));
  }

  Rental(Movie movie, int daysRented, Date startDate){
    this.movie = movie;
    this.daysRented = daysRented;
    this.startDate = startDate;
  }

  public int getDaysRented() {
    return daysRented;
  }

  public Movie getMovie() {
    return movie;
  }

  public Customer getCustomer() {
    return customer;
  }

  public LocalDate getStartDate() {
    return startDate.toLocalDate();
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

double amount() {
	double thisAmount = 0;
      //determine amounts for each line
      switch (movie.getPriceCode()) {
        case Movie.REGULAR:
          thisAmount += 2;
          if (getDaysRented() > 2)
            thisAmount += (getDaysRented() - 2) * 1.5;
          break;
        case Movie.NEW_RELEASE:
          thisAmount += getDaysRented() * 3;
          break;
        case Movie.CHILDRENS:
          thisAmount += 1.5;
          if (daysRented > 3)
            thisAmount += (getDaysRented() - 3) * 1.5;
          break;
      }
	return thisAmount;
}

int frequentRenterPoints() {
	int frequentRenterPoints = 1;
      if (isBonusApplicable()) frequentRenterPoints++;
	return frequentRenterPoints;
}

private boolean isBonusApplicable() {
	return (movie.getPriceCode() == Movie.NEW_RELEASE)
          &&
          daysRented> 1;
}

}