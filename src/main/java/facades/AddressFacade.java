package facades;

import entities.Address;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Dennis & Christian
 */
public class AddressFacade {

    private static AddressFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private AddressFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static AddressFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AddressFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Address addAddress(Address address) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();
        em.persist(address);
        em.getTransaction().commit();
        em.close();
        return address;
    }
  
    public Address editAddress(Address a){
        EntityManager em = getEntityManager();
        
        Address address = em.find(Address.class, a.getId());
        address.setPersons(a.getPersons());
        
        em.getTransaction().begin();
        em.merge(address);
        em.getTransaction().commit();
        em.close();
        return address;
    }
    
        public Address deleteAddress(int id){
        EntityManager em = getEntityManager();
        
        Address address = em.find(Address.class, id);
        
        em.getTransaction().begin();
        em.remove(address);
        em.getTransaction().commit();
        em.close();
        
        return address;
    }
        
    public List<Address> getAllAddress(){
        EntityManager em = getEntityManager();
        
        TypedQuery tq = em.createNamedQuery("Address.all", Address.class);
        return tq.getResultList();
        
    }
}
