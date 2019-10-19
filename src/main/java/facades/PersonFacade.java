package facades;

import dto.AddressDto;
import dto.HobbyDto;
import dto.PersonDto;
import dto.PhoneDto;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import errorhandling.AddressNotFoundException;
import errorhandling.CityInfoNotFoundException;
import errorhandling.PhoneNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
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
        EntityManager em = getEntityManager();

        TypedQuery tq = em.createNamedQuery("Person.getAll", Person.class);
        List<PersonDto> dto = new PersonDto(tq.getResultList()).getAll();

        return dto;

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
        person.setHobbies(hobbyCheck(p.getHobbies(), person));

        person.setPhones(phoneCheck(p.getPhones(), person));

        em.getTransaction().begin();
        em.merge(person);
        em.getTransaction().commit();

        em.close();

        return new PersonDto(person);

    }

    public PersonDto deletePerson(int id) {
        EntityManager em = getEntityManager();

        Person person = em.find(Person.class, id);

        em.getTransaction().begin();
        em.remove(person);
        em.getTransaction().commit();
        em.close();

        return new PersonDto(person);
    }
    public int getPersonCountOfHobby(String hobbyName){
        int count = getPersonsWithHobby(hobbyName).size();
        return count;
    }
    public List<PersonDto> getPersonsFromCity(String city){
        EntityManager em = getEntityManager();
        
        TypedQuery q = em.createQuery("SELECT p FROM Person p JOIN FETCH p.address.cityInfo ci WHERE ci.city = :city", Person.class);
        q.setParameter("city", city);
        List<Person> p = q.getResultList();
        return new PersonDto(p).getAll();
    }
    public List<PersonDto> getPersonsWithHobby(String hobbyName) {
        EntityManager em = getEntityManager();

        TypedQuery q = em.createQuery("SELECT p FROM Person p JOIN FETCH p.hobbies h WHERE h.name = :name", Person.class);
        q.setParameter("name", hobbyName);
        List<Person> p = q.getResultList();
        return new PersonDto(p).getAll();
    }

    public PersonDto getPersonInfoByPhone(String phoneNumber) throws PhoneNotFoundException {
        try {

            EntityManager em = getEntityManager();

            TypedQuery q = (TypedQuery) em.createQuery(
                    "SELECT p FROM Person p JOIN FETCH p.phones t WHERE t.number = :phoneNumber", Person.class);
            q.setParameter("phoneNumber", phoneNumber);
            Person p = (Person) q.getSingleResult();

            p = setPersonInfo(p);
            PersonDto dto = new PersonDto(p);

            return dto;

        } catch (NoResultException ex) {
            throw new PhoneNotFoundException("PhoneNumber does not exist");
        }

    }

    private Person setPersonInfo(Person p) {
        List<Phone> ph = getPersonsPhones(p.getId());
//        Address a = getPersonsAddress(p);
        List<Hobby> hobbies = getPersonsHobbies(p);

        p.setPhones(ph);
        p.setHobbies(hobbies);
//        p.setAddress(a);
        return p;
    }

    private List<Hobby> getPersonsHobbies(Person p) {
        EntityManager em = getEntityManager();

        TypedQuery q = em.createQuery("SELECT h FROM Hobby h JOIN FETCH h.persons p WHERE p.id = :id", Hobby.class);
        q.setParameter("id", p.getId());
        List<Hobby> h = q.getResultList();
        return h;
    }

//    private Address getPersonsAddress(Person p) {
//        EntityManager em = getEntityManager();
//
//        TypedQuery q = em.createQuery("SELECT a FROM Address a JOIN FETCH a.persons p WHERE p.id = :id", Address.class);
//        q.setParameter("id", p.getId());
//        Address a = (Address) q.getSingleResult();
//
//        return a;
//    }

    private List<Phone> getPersonsPhones(int id) {
        EntityManager em = getEntityManager();
        TypedQuery q = em.createQuery("SELECT p FROM Phone p JOIN FETCH p.person per WHERE per.id = :id", Phone.class);
        q.setParameter("id", id);
        List<Phone> p = q.getResultList();
        return p;

    }

    private List<Phone> phoneCheck(List<PhoneDto> pDtoList, Person p) {
        List<Phone> phones = new ArrayList();
        for (PhoneDto pDto : pDtoList) {
            Phone phone = new Phone(pDto.getNumber(), pDto.getDescription());
            phone.setPerson(p);
            phones.add(phone);
        }
        return phones;
    }

    private List<Hobby> hobbyCheck(List<HobbyDto> hDtoList, Person p) {
        EntityManager em = getEntityManager();
        List<Hobby> hList = new ArrayList();

        for (HobbyDto hDto : hDtoList) {
            TypedQuery tq = em.createNamedQuery("Hobby.check", Hobby.class);
            tq.setParameter("name", hDto.getName());

            try {
                Hobby h = (Hobby) tq.getSingleResult();
                h.getPersons().add(p);

                hList.add(h);
            } catch (NoResultException ex) {
                Hobby newH = new Hobby(hDto.getName(), hDto.getDescription());
                newH.getPersons().add(p);
                hList.add(newH);
            }
        }
        return hList;

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
}
