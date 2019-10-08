package dto;

import entities.Address;
import entities.Person;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ryge
 */
public class AddressDto {

    private int id;
    private String street;
    private String houseNumber;
    private String story;
    private List<PersonDto> persons;
    private CityInfoDto cityInfo;
    private ArrayList<AddressDto> all; 

    public AddressDto() {
    }

    public AddressDto(String street, String houseNumber, String story, int zipcode, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.story = story;
        this.cityInfo = new CityInfoDto(zipcode, city);
        persons = new ArrayList();
        
    }
    
    public AddressDto(Address a) {
//        this.id = a.getId();
        this.street = a.getStreet();
        this.houseNumber = a.getHouseNumber();
        this.story = a.getStory();
        this.cityInfo = new CityInfoDto(a.getCityInfo());
        persons = new PersonDto(a.getPersons()).getAll();
      
    }

    public AddressDto(List<Address> a) {
        all = new ArrayList();
        a.forEach((address) -> {
            all.add(new AddressDto(address));
        });
        
    }

    public ArrayList<AddressDto> getAll() {
        return all;
    }

    public void setAll(ArrayList<AddressDto> all) {
        this.all = all;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public List<PersonDto> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonDto> persons) {
        this.persons = persons;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public CityInfoDto getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfoDto cityInfo) {
        this.cityInfo = cityInfo;
    }

   
    
}
