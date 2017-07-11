package model;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;
	private String name; 
	private String surname;
	
	/**
	 * fetch indica como cargar el objecto referenciado. Puede ser tipo:
	 * FetchType.LAZY
	 * FetchType.EAGER
	 */
	@OneToOne(fetch=FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.REMOVE},optional=false)
	@JoinColumn(name="id")
	private Address address; 
			
	public Employee(){
		id=-1;
		}

	public int getId() {
		return id;
	}

    // GETTERS AND SETTERS

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return name;
	}

	public void setFirstName(String firstName) {
		this.name = firstName;
	}

	public String getLastName() {
		return surname;
	}

	public void setLastName(String lastname) {
		this.surname = lastname;
	}

	

}
