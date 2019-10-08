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
    private List<Hobby> hobbies;
    private List<Phone> phones;
    private Address address;
    private ArrayList<PersonDto> all;

    public PersonDto(String email, String firstName, String lastName, String hName, String hDesc, String pNumber, String pDesc, String streetName, String houseNumber, String story, int zipcode, String city) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobbies = new ArrayList();
        hobbies.add(new Hobby(hName, hDesc));
        this.phones = new ArrayList();
        phones.add(new Phone(pNumber, pDesc));
        this.address = new Address(streetName, houseNumber, story);
        address.setCityInfo(new CityInfo(zipcode, city));

    }

    public PersonDto(Person p) {
        this.id = p.getId();
        this.email = p.getEmail();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.hobbies = p.getHobbies();
        this.phones = p.getPhones();
        this.address = p.getAddress();
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

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<PersonDto> getAll() {
        return all;
    }

    public void setAll(ArrayList<PersonDto> all) {
        this.all = all;
    }

}
