package ma.aiac.dao;

import java.util.List;

import javax.persistence.EntityManager;

import ma.aiac.entities.Person;

public class JPAPersonDAO implements PersonDAO {

	private EntityManager em;
	
	public JPAPersonDAO(EntityManager em){
		this.em = em;
	}
	
	
	public void insert(Person person) {
        try {
        	em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        }catch(Exception e){
        	if(em.getTransaction().isActive()) em.getTransaction().rollback();
        	e.printStackTrace();
        }
    }
    
	public void update(Person person) {
		Person foundPerson = getById(person.getId());
		if(foundPerson == null) return;
		try {
        	em.getTransaction().begin();
        	foundPerson.setAge(person.getAge());
        	foundPerson.setName(person.getName());
            em.persist(foundPerson);
            em.getTransaction().commit();
        }catch(Exception e){
        	if(em.getTransaction().isActive()) em.getTransaction().rollback();
        	e.printStackTrace();
        }
    }
	
    public void delete(long id) {
    	try {
        	em.getTransaction().begin();
        	Person person = getById(id);
        	if(!em.contains(person)) em.merge(person);
            em.remove(person);
            em.getTransaction().commit();
        }catch(Exception e){
        	if(em.getTransaction().isActive()) em.getTransaction().rollback();
        	e.printStackTrace();
        }
    }
    
    public Person getById(long id) {
        return em.find(Person.class,id);
    }

    public List<Person> getByName(String name) {
    	return em.createQuery("select p from Person p where p.name = "+name,
    			Person.class).
    			getResultList();
    }

    public List<Person> getAll() {
        return em.createQuery("select p from Person p",
        		Person.class).
        		getResultList();
    }

    public List<Person> getByAge(int age) {
    	return em.createQuery("select p from Person p where p.age = "+age,
    			Person.class).
    			getResultList();
    }

	
}
