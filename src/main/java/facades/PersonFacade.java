package facades;

import entities.Person;
import entities.RenameMe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public long getRenameMeCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long renameMeCount = (long) em.createQuery("SELECT COUNT(r) FROM RenameMe r").getSingleResult();
            return renameMeCount;
        } finally {
            em.close();
        }

    }
    
    public Person getPerson(int id){
        EntityManager em = getEntityManager();
        
        Person p = em.find(Person.class, id);
        em.close();
        return p;
    }
    
    public Person addPerson(Person person) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.close();
        return person;
    }

    public Person editPerson(Person p){
        EntityManager em = getEntityManager();
        
        Person person = em.find(Person.class, p.getId());
        
        person.setFirstName(p.getFirstName());
        person.setLastName(p.getLastName());
        person.setEmail(p.getEmail());
        person.setAddress(p.getAddress());
        person.setHobbies(p.getHobbies());
        person.setPhones(p.getPhones());
        
        em.getTransaction().begin();
        em.merge(person);
        em.getTransaction().commit();
        
        em.close();
        
        return person;
        
    }
    
    public Person deletePerson(int id){
        EntityManager em = getEntityManager();
        
        Person person = em.find(Person.class, id);
        
        em.getTransaction().begin();
        em.remove(person);
        em.getTransaction().commit();
        em.close();
        
        return person;
    }

}