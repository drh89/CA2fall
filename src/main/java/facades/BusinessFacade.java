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
import errorhandling.CityInfoNotFoundException;
import errorhandling.HobbyNotFoundException;
import errorhandling.PhoneNotFoundException;
import java.util.ArrayList;
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

    public PersonDto addPerson(PersonDto p) {

        PersonDto person = pf.addPerson(p);

        person.setAddress(p.getAddress());

        person.getAddress().setCityInfo(p.getAddress().getCityInfo());
        
        person.setHobbies(p.getHobbies());
        
        person.setPhones(p.getPhones());

        return editPerson(person);
    }


    public PersonDto editPerson(PersonDto p) {
        return pf.editPerson(p);
    }

    public PersonDto getPerson(int id) {
        return pf.getPerson(id);
    }
    
    public List<PersonDto> getPersonsWithHobby(String hobbyName) throws HobbyNotFoundException{
        return pf.getPersonsWithHobby(hobbyName);
    }
    
    public List<PersonDto> getPersonsFromCity(String city) throws CityInfoNotFoundException{
        return pf.getPersonsFromCity(city);
    }
    
    public PersonDto getPersonInfoByPhone(String phoneNumber) throws PhoneNotFoundException{
        return pf.getPersonInfoByPhone(phoneNumber);
    }
    public String getPersonCountOfHobby(String hobbyName) throws HobbyNotFoundException{
        return pf.getPersonCountOfHobby(hobbyName);
    }
    public List<PersonDto> getAllPersons(){
        return pf.getAllPersons();
    }
    public PersonDto deletePerson(int id) {
        return pf.deletePerson(id);
    }

    public List<Address> getAllAddress() {
        return af.getAllAddress();
    }

    public Address addAddress(Address a) {
        return af.addAddress(a);
    }

    public Address editAddress(Address a) {
        return af.editAddress(a);
    }

    public Address deleteAddress(int id) {
        return af.deleteAddress(id);
    }

    public List<CityInfo> getAllCityInfos() {
        return cif.getAllCityInfos();
    }

    public CityInfo addCityInfo(CityInfo ci) {
        return cif.addCity(ci);
    }

    public CityInfo ediCityInfo(CityInfo ci) {
        return cif.editCityInfo(ci);
    }

    public CityInfo deleteCityInfo(int id) {
        return cif.deleteCity(id);
    }

    public List<Hobby> getAllHobbies() {
        return hf.getAllHobbies();
    }

    public Hobby addHobby(Hobby h) {
        return hf.addHobby(h);
    }

    public Hobby editHobby(Hobby h) {
        return hf.editHobby(h);
    }

    public Hobby deleteHobby(int id) {
        return hf.deleteHobby(id);
    }
}
