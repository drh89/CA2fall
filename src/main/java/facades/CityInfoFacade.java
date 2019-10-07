package facades;

import entities.CityInfo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Dennis & Christian
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

    public CityInfo addCity(CityInfo city) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();
        em.persist(city);
        em.getTransaction().commit();
        em.close();
        return city;
    }

    public CityInfo editCityInfo(CityInfo ci) {
        EntityManager em = getEntityManager();
        CityInfo cityInfo = em.find(CityInfo.class, ci.getId());

        cityInfo.setAddresss(ci.getAddresss());

        em.getTransaction().begin();
        em.merge(cityInfo);
        em.getTransaction().commit();
        em.close();
        return cityInfo;
    }

    public CityInfo deleteCity(int id) {
        EntityManager em = getEntityManager();

        CityInfo city = em.find(CityInfo.class, id);

        em.getTransaction().begin();
        em.remove(city);
        em.getTransaction().commit();
        em.close();

        return city;
    }

    public List<CityInfo> getAllCityInfos() {
        EntityManager em = getEntityManager();

        TypedQuery tq = em.createNamedQuery("CityInfo.all", CityInfo.class);
        return tq.getResultList();
    }
}
