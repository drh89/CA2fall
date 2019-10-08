package facades;

import dto.CityInfoDto;
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

    public CityInfoDto addCity(CityInfoDto ciDto) {
        EntityManager em = getEntityManager();

        CityInfo ci = new CityInfo(ciDto.getZipcode(), ciDto.getCity());
        ci.setAddresss(ci.addToAdresss(ciDto.getAddress()));
        
        

        em.getTransaction().begin();
        em.persist(ci);
        em.getTransaction().commit();
        em.close();
        return new CityInfoDto(ci);
    }

    public CityInfoDto editCityInfo(CityInfoDto ciDto) {
        EntityManager em = getEntityManager();
        CityInfo cityInfo = em.find(CityInfo.class, ciDto.getId());

        cityInfo.setZipcode(ciDto.getZipcode());
        cityInfo.setCity(ciDto.getCity());
        cityInfo.setAddresss(cityInfo.addToAdresss(ciDto.getAddress()));
        
//        ciDto.getAddress().forEach((addDto)->{
//            cityInfo.addToAdresss(addDto.getStreet(), addDto.getHouseNumber(), addDto.getStory());
//        });

        em.getTransaction().begin();
        em.merge(cityInfo);
        em.getTransaction().commit();
        em.close();
        return new CityInfoDto(cityInfo);
    }

    public CityInfoDto deleteCity(int id) {
        EntityManager em = getEntityManager();

        CityInfo ci = em.find(CityInfo.class, id);

        em.getTransaction().begin();
        em.remove(ci);
        em.getTransaction().commit();
        em.close();

        return new CityInfoDto(ci);
    }

    public List<CityInfoDto> getAllCityInfos() {
        EntityManager em = getEntityManager();

        TypedQuery tq = em.createNamedQuery("CityInfo.all", CityInfo.class);
        return new CityInfoDto(tq.getResultList()).getAll();
    }
}
