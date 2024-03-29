/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Hobby;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Dennis
 */
public class HobbyFacade {
    private static HobbyFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private HobbyFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static HobbyFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HobbyFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Hobby addHobby(Hobby h){
        EntityManager em = getEntityManager();

        em.getTransaction().begin();
        em.persist(h);
        em.getTransaction().commit();
        em.close();
        return h;
    }
    
    public Hobby editHobby(Hobby h){
        EntityManager em = getEntityManager();
        
        Hobby hobby = em.find(Hobby.class, h.getId());
        hobby.setName(h.getName());
        hobby.setDescription(h.getDescription());
        hobby.setPersons(h.getPersons());
        return hobby;
    }
    
    public Hobby deleteHobby(int id){
        EntityManager em = getEntityManager();
        
        Hobby h = em.find(Hobby.class, id);
        
        em.getTransaction().begin();
        em.remove(h);
        em.getTransaction().commit();
        em.close();
        
        return h;
    }
    public List<Hobby> getAllHobbies(){
        EntityManager em = getEntityManager();
        
        TypedQuery tq = em.createNamedQuery("Hobby.all", Hobby.class);
        return tq.getResultList();
    }
}
