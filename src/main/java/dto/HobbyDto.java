package dto;

import entities.Hobby;
import entities.Person;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ryge
 */
public class HobbyDto {
    
    private int id;
    private String name;
    private String description;
    private List<Person> persons;
    private ArrayList<HobbyDto> all;
    
    public HobbyDto(){
    }
    
    public HobbyDto(String name, String description) {
        this.name = name;
        this.description = description;
        
    }

    public HobbyDto(Hobby h) {
        this.id = h.getId();
        this.name = h.getName();
        this.description = h.getDescription();
        this.persons = h.getPersons();
    }
    

    public HobbyDto(List<Hobby> h) {
        all = new ArrayList();
        h.forEach((hobby) -> {
            all.add(new HobbyDto(hobby));
        });
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public ArrayList<HobbyDto> getAll() {
        return all;
    }

    public void setAll(ArrayList<HobbyDto> all) {
        this.all = all;
    }

}
