package ma.aiac;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ma.aiac.dao.JPAPersonDAO;
import ma.aiac.dao.PersonDAO;
import ma.aiac.entities.Person;

public class JPAExampleMain {

	public static void main(String[] args) {
		
		//Connect
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_EXAMPLE");
		PersonDAO pdao = new JPAPersonDAO(emf.createEntityManager());
		
		//Create objects
		Person p1 = new Person();
		p1.setName("Person 1");
		p1.setAge(21);
		
		Person p2 = new Person();
		p1.setName("Person 1");
		p1.setAge(21);
		
		//Insert objects
		pdao.insert(p1);
		pdao.insert(p2);
		
		//Get all
		List<Person> all = pdao.getAll();
		System.out.println("Before update and delete");
		printList(all);
		
		//Update
		Person toUpdate = all.get(0);
		toUpdate.setName("Updated person");
		pdao.update(toUpdate);
		
		//Delete
		Person toDelete = all.get(1);
		pdao.delete(toDelete.getId());
		
		all = pdao.getAll();
		System.out.println("After update and delete");
		printList(all);

	}
	
	private static void printList(List<Person> list){
		for (Person p : list) System.out.println(p);
	}

}
