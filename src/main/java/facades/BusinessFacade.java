/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.AddressDto;
import dto.CityInfoDto;
import dto.HobbyDto;
import dto.PersonDto;
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

//    public Person addPerson(Person p) {
//        List<Address> allAddress = getAllAddress();
//        List<CityInfo> allCityInfos = getAllCityInfos();
//        Person person = pf.addPerson(new Person(p.getEmail(), p.getFirstName(), p.getLastName()));
//
//        for (Address a : allAddress) {
//            if (a.getStreet().equalsIgnoreCase(p.getAddress().getStreet()) && a.getHouseNumber().equalsIgnoreCase(p.getAddress().getHouseNumber())
//                    && a.getStory().equalsIgnoreCase(p.getAddress().getStory())) {
//
//                person.setAddress(a);
//                return editPerson(person);
//            }
//        }
//
//        person.setAddress(p.getAddress());
//
//        for (CityInfo ci : allCityInfos) {
//            if (ci.getCity().equalsIgnoreCase(p.getAddress().getCityInfo().getCity())
//                    && ci.getZipcode() == p.getAddress().getCityInfo().getZipcode()) {
//
//                person.getAddress().setCityInfo(ci);
//
//                return editPerson(person);
//            }
//        }
//
//        person.getAddress().setCityInfo(p.getAddress().getCityInfo());
//        return editPerson(person);
//    }
//    
//    public Person addHobbyToPerson(Person p, Hobby h){
//        List<Hobby> allHobbies = getAllHobbies();
//        
//        for (Hobby hobby : allHobbies) {
//            if(hobby.getName().equalsIgnoreCase(h.getName())){
//                p.getHobbies().add(hobby);
//                p = editPerson(p);
//                return p;
//            }
//        }
//        p.getHobbies().add(h);
//        p = editPerson(p);
//        return p;
//    }

    public PersonDto editPerson(PersonDto p) {
        return pf.editPerson(p);
    }

    public PersonDto getPerson(int id) {
        return pf.getPerson(id);
    }
    public PersonDto deletePerson(int id){
        return pf.deletePerson(id);
    }
    public List<AddressDto> getAllAddress(){
        return af.getAllAddress();
    }
    public AddressDto addAddress(AddressDto a){
        return af.addAddress(a);
    }
    public AddressDto editAddress(AddressDto a){
         return af.editAddress(a);
    }
    public AddressDto deleteAddress(int id){
        return af.deleteAddress(id);
    }
    public List<CityInfoDto> getAllCityInfos(){
        return cif.getAllCityInfos();
    }
    public CityInfoDto addCityInfo(CityInfoDto ci){
        return cif.addCity(ci);
    }
    public CityInfoDto ediCityInfo(CityInfoDto ci){
        return cif.editCityInfo(ci);
    }
    public CityInfoDto deleteCityInfo(int id){
        return cif.deleteCity(id);
    }
    public List<HobbyDto> getAllHobbies(){
        return hf.getAllHobbies();
    }
    public HobbyDto addHobby(HobbyDto h){
        return hf.addHobby(h);
    }
    public HobbyDto editHobby(HobbyDto h){
        return hf.editHobby(h);
    }
    public HobbyDto deleteHobby(int id){
        return hf.deleteHobby(id);
    }
}
