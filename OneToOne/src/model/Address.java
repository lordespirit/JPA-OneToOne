package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
	    
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
	    private String street;
		private int number;
		private String webpage; 
		private String zipcode;
		private String city;

		public Address(){
			id=-1;
		}
	
	    // GETTERS AND SETTERS
	    
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public String getWebpage() {
			return webpage;
		}

		public void setWebpage(String webpage) {
			this.webpage = webpage;
		}

		public String getZipcode() {
			return zipcode;
		}

		public void setZipcode(String zipcode) {
			this.zipcode = zipcode;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		
}