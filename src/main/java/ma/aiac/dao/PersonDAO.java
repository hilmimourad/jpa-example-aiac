package ma.aiac.dao;

import java.util.List;

import ma.aiac.entities.Person;


public interface PersonDAO {

	void insert(Person person);
	void update(Person person);
	void delete(long id);
	
	
	Person getById(long id);
	List<Person> getByName(String name);
	List<Person> getAll();
	List<Person> getByAge(int age);
	
	
}

