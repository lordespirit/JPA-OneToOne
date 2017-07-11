package test;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import dao.DBManager;
import model.Address;
import model.Employee;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestOneToOne {

	DBManager dbManager;
	Employee employee;
	
	@Before
	public void init(){
		dbManager = new DBManager();
		dbManager.connect();
		dbManager.deleteAll(Employee.class);
	}
	
	@Test
	public void testInsert(){
		
	Address address = getMockAddress("Calle test", 3306);
	Employee employee = getMockEmployee("Eduard", "Vallés");
	
	employee.setAddress(address);
	
	dbManager.connect();
	dbManager.insert(employee);
	employee.getAddress();
	dbManager.close();
		
	}
	
	private Employee getMockEmployee(String name, String surname){
		Employee employee = new Employee();
		employee.setFirstName(name);
		employee.setLastName(surname);
		return employee;
		
	}
	
	private Address getMockAddress(String street, int number){
		Address address = new Address();
		address.setStreet(street);
		address.setNumber(number);
		address.setCity("Barcelona");
		address.setZipcode("08028");
		return address;
		
	}
	
}