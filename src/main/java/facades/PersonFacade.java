package facades;

import dto.PersonDto;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import entities.RenameMe;
import errorhandling.AddressNotFoundException;
import errorhandling.CityInfoNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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

    public PersonFacade(EntityManagerFactory emf) {
        this.emf = emf;
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
    
    public List<PersonDto> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("PersonDto.findAll", PersonDto.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    public PersonDto getPersonByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("PersonDto.findByEmail", PersonDto.class).setParameter("email", email).getSingleResult();
        } finally {
            em.close();
        }
    }

    public PersonDto getPerson(int id) {
        EntityManager em = getEntityManager();

        Person p = em.find(Person.class, id);
        em.close();
        return new PersonDto(p);
    }

    public PersonDto addPerson(PersonDto p) {
        EntityManager em = getEntityManager();

        Person person = new Person(p.getFirstName(), p.getLastName(), p.getEmail());

        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.close();
        return new PersonDto(person);
    }

    public PersonDto editPerson(PersonDto p) {
        EntityManager em = getEntityManager();

        Person person = em.find(Person.class, p.getId());

        person.setFirstName(p.getFirstName());
        person.setLastName(p.getLastName());
        person.setEmail(p.getEmail());

        try {
            person.setAddress(checkAddress(new Address(p.getAddress().getStreet(), p.getAddress().getHouseNumber(), p.getAddress().getStory())));
        } catch (AddressNotFoundException ex) {

            person.setAddress(new Address(p.getAddress().getStreet(), p.getAddress().getHouseNumber(), p.getAddress().getStory()));

            try {
                person.getAddress().setCityInfo(checkCityInfo(new CityInfo(p.getAddress().getCityInfo().getZipcode(), p.getAddress().getCityInfo().getCity())));

            } catch (CityInfoNotFoundException e) {
                person.getAddress().setCityInfo(new CityInfo(p.getAddress().getCityInfo().getZipcode(), p.getAddress().getCityInfo().getCity()));

            }
        }

        person.getHobbies().clear();
        p.getHobbies().forEach((hDto) -> {
            person.getHobbies().add(new Hobby(hDto.getName(), hDto.getDescription()));
        });

        person.getPhones().clear();
        p.getPhones().forEach((pDto) -> {
            Phone phone = new Phone(pDto.getNumber(), pDto.getDescription());
            phone.setPerson(person);
            person.getPhones().add(phone);

        });

        em.getTransaction().begin();
        em.merge(person);
        em.getTransaction().commit();

        em.close();

        return new PersonDto(person);

    }

    private CityInfo checkCityInfo(CityInfo ci) throws CityInfoNotFoundException {
        EntityManager em = getEntityManager();

        TypedQuery tq = em.createNamedQuery("CityInfo.check", CityInfo.class);
        tq.setParameter("zipcode", ci.getZipcode());
        tq.setParameter("city", ci.getCity());

        try {
            CityInfo cityInfo = (CityInfo) tq.getSingleResult();
        } catch (NoResultException ex) {
            throw new CityInfoNotFoundException("");
        }
        return (CityInfo) tq.getSingleResult();
    }

    private Address checkAddress(Address a) throws AddressNotFoundException {
        EntityManager em = getEntityManager();

        TypedQuery tq = em.createNamedQuery("Address.check", Address.class);
        tq.setParameter("street", a.getStreet());
        tq.setParameter("houseNumber", a.getHouseNumber());
        tq.setParameter("story", a.getStory());
        try {
            Address ad = (Address) tq.getSingleResult();
        } catch (NoResultException ex) {
            throw new AddressNotFoundException("");
        }

        return (Address) tq.getSingleResult();

    }

    public Person deletePerson(int id) {
        EntityManager em = getEntityManager();

        Person person = em.find(Person.class, id);

        em.getTransaction().begin();
        em.remove(person);
        em.getTransaction().commit();
        em.close();

        return person;
    }

}
