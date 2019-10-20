package dto;

import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ryge
 */
public class PersonDto {

    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private List<HobbyDto> hobbies;
    private List<PhoneDto> phones;
    private AddressDto address;
    private ArrayList<PersonDto> all;

    public PersonDto() {
        
    }
    
    
    //Used to populate DB
    
    public PersonDto(String firstName, String lastName, String email, String hName, String hDesc, String pNumber, String pDesc, String streetName, String houseNumber, String story, int zipcode, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        
        this.hobbies = new ArrayList();
        hobbies.add(new HobbyDto(hName, hDesc));
        this.phones = new ArrayList();
        phones.add(new PhoneDto(pNumber, pDesc));
        this.address = new AddressDto(streetName, houseNumber, story, zipcode, city);
        address.setCityInfo(new CityInfoDto(zipcode, city));

    }


    public PersonDto(Person p) {
        this.id = p.getId();
        this.email = p.getEmail();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.hobbies = new HobbyDto(p.getHobbies()).getAll();
        this.phones = new PhoneDto(p.getPhones()).getAll();
//        this.address = new AddressDto( p.getAddress());
    }

    public PersonDto(List<Person> p) {
        all = new ArrayList();
        p.forEach((person) -> {
            all.add(new PersonDto(person));
        });
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<HobbyDto> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<HobbyDto> hobbies) {
        this.hobbies = hobbies;
    }

    public List<PhoneDto> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDto> phones) {
        this.phones = phones;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public ArrayList<PersonDto> getAll() {
        return all;
    }

    public void setAll(ArrayList<PersonDto> all) {
        this.all = all;
    }

}
