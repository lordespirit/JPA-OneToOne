package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Employee {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name; 
	private String surname;
	
	
	/**
	 * fetch indica como cargar el objeto referenciado
	 * FetchType.LAZY
	 * FetchType.EAGER
	 */
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE},optional=false)
	//@JoinColumn(name = "id")
	private Address address; 

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Address getAdress() {
		return address;
	}
	public void setAdress(Address adress) {
		this.address = adress;
	} 
	
}