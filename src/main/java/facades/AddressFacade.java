/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Address;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Dennis
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
    public void editAddress(Address a){
        EntityManager em = getEntityManager();
        
        Address address = em.find(Address.class, a.getId());
        address.setPersons(a.getPersons());
        
        em.getTransaction().begin();
        em.merge(address);
        em.getTransaction().commit();
        em.close();
        
    }
    public List<Address> getAllAddress(){
        EntityManager em = getEntityManager();
        
        TypedQuery tq = em.createNamedQuery("Address.all", Address.class);
        return tq.getResultList();
        
    }
}
