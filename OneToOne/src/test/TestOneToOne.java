package test;


import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DBManager;
import jdk.nashorn.internal.AssertsEnabled;
import model.Address;

import model.Employee;

public class TestOneToOne {
	DBManager dbManager; 
	Employee employee; 
	
	@Before
	public void init(){
		dbManager =   new DBManager(); 	
		dbManager.connect();
		dbManager.deleteAll(Employee.class); 
		dbManager.close(); 		
	}
	
	//@Test
	public void testInsert(){	
		Address address = getMockAddress("Calle test", 3306);
		Employee employee =getMockEmployee("Pedro","Picapiedra"); 
		employee.setAdress(address);
		
		dbManager.connect();
			dbManager.insert(employee);
			//employee.getAdress(); 
		dbManager.close(); 	
		
		Assert.assertEquals(true,employee.getId()>0);
		Assert.assertEquals(true,employee.getAdress().getId()>0);		
	}
	
	//@Test
	public void testSelect(){
	
		Address address1 = getMockAddress("Calle Mayor", 25);
		Employee employee1 =getMockEmployee("Jose","Garcia"); 
		Address address2 = getMockAddress("Avenida Larga", 147);
		Employee employee2 =getMockEmployee("Anna","Moral"); 
		address2.setCity("Girona");
		address2.setZipCode("12345");
		employee1.setAdress(address1);
		employee2.setAdress(address2);
		
		dbManager.connect();
		dbManager.insert(employee1);
		dbManager.insert(employee2);
		
		ArrayList<Employee> result;
		result = dbManager.selectEqual(Employee.class, "address.city", "Girona");
		dbManager.close(); 	
		
		Assert.assertEquals("Girona", result.get(0).getAdress().getCity());
		
	}
	
	@Test
	public void testUpdate(){
		
		Employee employee = getMockEmployee("Teresa","Riera");
		Address address1 = getMockAddress("Calle Balmes", 129);
		address1.setCity("Tarragona");
		address1.setZipCode("98765");
		employee.setAdress(address1);

		dbManager.connect();
		dbManager.insert(employee);
		dbManager.close(); 	

		// Aquí empiezo un update del empleado (employee), se obtiene el objeto y se realizan cambios dentro de una transaccion.
		// Cuando los cambios estan hechos se realiza el 'commit' para que el cambio se haga efectivo.
		// Este modo de actualizar los datos evita la creación de un nuevo registro de 'address' y evita basura en la BBDD.
		
		dbManager.connect();
		dbManager.getEntityManager().getTransaction().begin();
		
		Employee employeeUpdate = dbManager.getEntityManager().find(Employee.class, employee.getId());
		
		employeeUpdate.setName("Pedro");
		employeeUpdate.setSurname("Rodriguez");
		Address newAddress = dbManager.getEntityManager().find(Address.class, address1.getId());
		newAddress.setCity("Lleida");
		employeeUpdate.setAdress(newAddress);
		
		dbManager.getEntityManager().getTransaction().commit();
		dbManager.close();
		
		Assert.assertEquals(true, employeeUpdate.getAdress().getId()>0);
		Assert.assertEquals("Lleida", employeeUpdate.getAdress().getCity());
		Assert.assertEquals("Pedro", employeeUpdate.getName());
		Assert.assertEquals("Rodriguez", employeeUpdate.getSurname());

		
	}

	private Employee getMockEmployee(String name, String surname) {
		 Employee employee = new Employee();
		 employee.setName(name);
		 employee.setSurname(surname);
		 return employee;
	}
	
	private	Address getMockAddress(String street, int number) {
		Address address = new Address(); 
		 address.setStreet(street);
		 address.setNumber(number);
		 address.setCity("Barcelona");
		 address.setZipCode("08001");
		return  address;
	}

}