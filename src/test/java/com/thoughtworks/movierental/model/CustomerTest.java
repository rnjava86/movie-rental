package com.thoughtworks.movierental.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CustomerTest {
	
	private Customer customer;
	@Before
	public void setup() {
    	customer = new Customer("New Customer", "example@email.com");
    	customer.addRental(new Rental(new Movie("Uri", Movie.NEW_RELEASE), 5));
    	customer.addRental(new Rental(new Movie("Kungfu Panda", Movie.CHILDRENS), 3));
    	customer.addRental(new Rental(new Movie("DDLJ", Movie.REGULAR), 4));		
	}
	
    @Test
    public void testThatStatementFunctionWorksCorrectly(){
    	assertEquals("Rental Record for New Customer\n" + 
    			"	Uri\t15.0\n" + 
    			"	Kungfu Panda\t1.5\n" + 
    			"	DDLJ\t5.0\n" + 
    			"Amount owed is 21.5\n" + 
    			"You earned 4 frequent renter points",customer.statement());
    	
    }
    
    @Test
    public void shouldGenerateAnHtmlStatement(){
        assertEquals("<h1>Rental Statement for <b>New Customer</b></h1><br/>"+
                    "Uri 15.0<br/>"+
                    "Kungfu Panda 1.5<br/>"+
                    "DDLJ 5.0<br/>"+
                    "Amount owed is <b>21.5</b><br/>"+
                    "You earned <b>4</b> frequent renter points",
                customer.htmlStatement());
    }



}