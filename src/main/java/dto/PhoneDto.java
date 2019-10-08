package dto;

import entities.Person;
import entities.Phone;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ryge <your.name at your.org>
 */
public class PhoneDto {

    private int id;
    private String number;
    private String description;
    private Person person;
    private ArrayList<PhoneDto> all;

    public PhoneDto() {
    }

    public PhoneDto(String number, String description) {
        this.number = number;
        this.description = description;

    }

    public PhoneDto(Phone p) {
        this.id = p.getId();
        this.number = p.getNumber();
        this.description = p.getDescription();
        this.person = p.getPerson();
    }

    public PhoneDto(List<Phone> p) {
        all = new ArrayList();
        p.forEach((phone) -> {
            all.add(new PhoneDto(phone));
        });
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ArrayList<PhoneDto> getAll() {
        return all;
    }

    public void setAll(ArrayList<PhoneDto> all) {
        this.all = all;
    }

}
