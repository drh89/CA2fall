package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDto;
import entities.Address;
import entities.CityInfo;
import entities.Person;
import entities.Phone;
import entities.RenameMe;
import errorhandling.PhoneNotFoundException;
import facades.BusinessFacade;
import utils.EMF_Creator;
import facades.PersonFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("xxx")
public class RenameMeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/CA2fall",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final BusinessFacade FACADE = BusinessFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

//    @Path("count")
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public String getRenameMeCount() {
//        long count = FACADE.getRenameMeCount();
//        //System.out.println("--------------->"+count);
//        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
//    }

    public static void main(String[] args) {
        
        PersonDto first = new PersonDto("Jens", "Hansen", "test@test.dk", "Football", "Teamsport where you kick to a ball", "22233344", "Jens Hansens Phone", "Hjalmersgade", "22", "2tv", 2200, "Copenhagen");
        PersonDto second = new PersonDto("Hanne", "Hansen", "test2@test.dk", "Handball", "Teamsport where you throw a ball", "28833344", "Hanne Hansens Phone", "Hjalmersgade", "22", "2tv", 2200, "Copenhagen");
        PersonDto third = new PersonDto("Bjarne", "Lund", "test3@test.dk", "Football", "Teamsport where you kick to a ball", "66233344", "Bjarne Lunds Phone", "Lortegade", "232", "3tv", 2200, "Copenhagen");
        System.out.println(FACADE.addPerson(first));
        System.out.println(FACADE.addPerson(second));
        System.out.println(FACADE.addPerson(third));
        
//            try{
//                PersonDto p = FACADE.getPersonInfoByPhone("28833344");
//                System.out.println(p.getPhones().get(0).getNumber());
//            }catch(PhoneNotFoundException ex){
//                System.out.println(ex.getMessage());
//            }
           
            
        
    }

}
