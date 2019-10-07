/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.CityInfo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Dennis
 */
public class CityInfoFacade {
    private static CityInfoFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CityInfoFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CityInfoFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CityInfoFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void editCityInfo(CityInfo ci){
        EntityManager em = getEntityManager();
        CityInfo cityInfo = em.find(CityInfo.class, ci.getId());
        
        cityInfo.setAddresss(ci.getAddresss());
        
        em.getTransaction().begin();
        em.merge(cityInfo);
        em.getTransaction().commit();
        em.close();
    }
    
    public List<CityInfo> getAllCityInfos(){
        EntityManager em = getEntityManager();
        
        TypedQuery tq = em.createNamedQuery("CityInfo.all", CityInfo.class);
        return tq.getResultList();
    }
}
