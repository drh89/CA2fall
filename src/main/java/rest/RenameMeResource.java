package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDto;
import entities.Address;
import entities.CityInfo;
import entities.Person;
import entities.RenameMe;
import facades.BusinessFacade;
import utils.EMF_Creator;
import facades.PersonFacade;
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

//    public static void main(String[] args) {
//        
//        PersonDto first = new PersonDto("test@test.dk", "Hanne", "Jensen");
//        first.setAddress(new Address("Hjalmersgade", "22", "2tv"));
//        first.getAddress().setCityInfo(new CityInfo(2200, "Copenhagen"));
//        first = FACADE.addPerson(first);
//        System.out.println(first.getId());
//        
//        Person second = new Person("est@test.dk", "Hans", "Jensen");
//        second.setAddress(new Address("Hjalmersgade", "22", "2tv"));
//        second.getAddress().setCityInfo(new CityInfo(2200, "Copenhagen"));
//        second = FACADE.addPerson(second);
//        System.out.println(second.getAddress().getStreet() + " " + second.getAddress().getCityInfo().getZipcode());
//
//        Person third = new Person("test2@test.dk", "Bo","Boesen");
//        third.setAddress(new Address("Bagergade", "12", "0th"));
//        third.getAddress().setCityInfo(new CityInfo(2200, "Copenhagen"));
//        third = FACADE.addPerson(third);
////        System.out.println(FACADE.deletePerson(1));
//        
//    }

}
