/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.HobbyDto;
import entities.Hobby;
import java.util.ArrayList;
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

    public HobbyDto addHobby(HobbyDto hDto) {
        EntityManager em = getEntityManager();

        Hobby h = new Hobby(hDto.getName(), hDto.getDescription());

        em.getTransaction().begin();
        em.persist(h);
        em.getTransaction().commit();
        em.close();
        return new HobbyDto(h);
    }

    public HobbyDto editHobby(HobbyDto hDto) {
        EntityManager em = getEntityManager();

        Hobby hobby = em.find(Hobby.class, hDto.getId());
        hobby.setName(hDto.getName());
        hobby.setDescription(hDto.getDescription());
        hobby.setPersons(hDto.getPersons());

        em.getTransaction().begin();
        em.merge(hobby);
        em.getTransaction().commit();
        em.close();

        return new HobbyDto(hobby);
    }

    public HobbyDto deleteHobby(int id) {
        EntityManager em = getEntityManager();

        Hobby h = em.find(Hobby.class, id);

        em.getTransaction().begin();
        em.remove(h);
        em.getTransaction().commit();
        em.close();

        return new HobbyDto(h);
    }

    public List<HobbyDto> getAllHobbies() {
        EntityManager em = getEntityManager();

        TypedQuery tq = em.createNamedQuery("Hobby.all", Hobby.class);
        return new HobbyDto(tq.getResultList()).getAll();
    }
}
