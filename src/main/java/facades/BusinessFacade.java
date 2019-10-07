/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Dennis
 */
public class BusinessFacade {

    private static BusinessFacade instance;
    private static EntityManagerFactory emf;
    private AddressFacade af;
    private CityInfoFacade cif;
    private HobbyFacade hf;
    private PersonFacade pf;

    //Private Constructor to ensure Singleton
    private BusinessFacade() {
        af = af.getFacadeExample(emf);
        cif = cif.getFacadeExample(emf);
        hf = hf.getFacadeExample(emf);
        pf = pf.getFacadeExample(emf);
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BusinessFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BusinessFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Person addPerson(Person p) {
        List<Address> allAddress = getAllAddress();
        List<CityInfo> allCityInfos = getAllCityInfos();
        Person person = pf.addPerson(new Person(p.getEmail(), p.getFirstName(), p.getLastName()));

        for (Address a : allAddress) {
            if (a.getStreet().equalsIgnoreCase(p.getAddress().getStreet()) && a.getHouseNumber().equalsIgnoreCase(p.getAddress().getHouseNumber())
                    && a.getStory().equalsIgnoreCase(p.getAddress().getStory())) {

                person.setAddress(a);
                return editPerson(person);
            }
        }

        person.setAddress(p.getAddress());

        for (CityInfo ci : allCityInfos) {
            if (ci.getCity().equalsIgnoreCase(p.getAddress().getCityInfo().getCity())
                    && ci.getZipcode() == p.getAddress().getCityInfo().getZipcode()) {

                person.getAddress().setCityInfo(ci);

                return editPerson(person);
            }
        }

        person.getAddress().setCityInfo(p.getAddress().getCityInfo());
        return editPerson(person);
    }
    
    public Person addHobbyToPerson(Person p, Hobby h){
        List<Hobby> allHobbies = getAllHobbies();
        
        for (Hobby hobby : allHobbies) {
            if(hobby.getName().equalsIgnoreCase(h.getName())){
                p.getHobbies().add(hobby);
                p = editPerson(p);
                return p;
            }
        }
        p.getHobbies().add(h);
        p = editPerson(p);
        return p;
    }

    public Person editPerson(Person p) {
        return pf.editPerson(p);
    }

    public Person getPerson(int id) {
        return pf.getPerson(id);
    }
    public Person deletePerson(int id){
        return pf.deletePerson(id);
    }
    public List<Address> getAllAddress(){
        return af.getAllAddress();
    }
    public Address addAddress(Address a){
        return af.addAddress(a);
    }
    public Address editAddress(Address a){
         return af.editAddress(a);
    }
    public Address deleteAddress(int id){
        return af.deleteAddress(id);
    }
    public List<CityInfo> getAllCityInfos(){
        return cif.getAllCityInfos();
    }
    public CityInfo addCityInfo(CityInfo ci){
        return cif.addCity(ci);
    }
    public CityInfo ediCityInfo(CityInfo ci){
        return cif.editCityInfo(ci);
    }
    public CityInfo deleteCityInfo(int id){
        return cif.deleteCity(id);
    }
    public List<Hobby> getAllHobbies(){
        return hf.getAllHobbies();
    }
    public Hobby addHobby(Hobby h){
        return hf.addHobby(h);
    }
    public Hobby editHobby(Hobby h){
        return hf.editHobby(h);
    }
    public Hobby deleteHobby(int id){
        return hf.deleteHobby(id);
    }
}
