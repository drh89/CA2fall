package facades;

import dto.AddressDto;
import entities.Address;
import entities.CityInfo;
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

    public AddressDto addAddress(AddressDto addressDto) {
        EntityManager em = getEntityManager();
        Address address = new Address(addressDto.getStreet(), addressDto.getHouseNumber(), addressDto.getStory());
        address.setCityInfo(new CityInfo(addressDto.getZipcode(), addressDto.getCity()));

        em.getTransaction().begin();
        em.persist(address);
        em.getTransaction().commit();
        em.close();
        return new AddressDto(address);
    }
  
    public AddressDto editAddress(AddressDto a){
        EntityManager em = getEntityManager();
        
        Address address = em.find(Address.class, a.getId());
        address.setStreet(a.getStreet());
        address.setHouseNumber(a.getHouseNumber());
        address.setStory(a.getStory());
        address.setPersons(a.getPersons());
        
        em.getTransaction().begin();
        em.merge(address);
        em.getTransaction().commit();
        em.close();
        return new AddressDto(address);
    }
    
        public AddressDto deleteAddress(int id){
        EntityManager em = getEntityManager();
        
        Address address = em.find(Address.class, id);
        
        em.getTransaction().begin();
        em.remove(address);
        em.getTransaction().commit();
        em.close();
        
        return new AddressDto(address);
    }
        
    public List<AddressDto> getAllAddress(){
        EntityManager em = getEntityManager();
        
        TypedQuery tq = em.createNamedQuery("Address.all", Address.class);
        return new AddressDto(tq.getResultList()).getAll();
        
    }
}
