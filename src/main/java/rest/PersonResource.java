package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.HobbyDto;
import dto.PersonDto;
import dto.PhoneDto;
import entities.Hobby;
import entities.Phone;
import errorhandling.CityInfoNotFoundException;
import errorhandling.HobbyNotFoundException;
import errorhandling.PhoneNotFoundException;
import facades.BusinessFacade;
import facades.PersonFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.EMF_Creator;

/**
 *
 * @author Ryge
 */
@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/CA2fall",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final BusinessFacade FACADE = BusinessFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        return Response.ok().entity(GSON.toJson(FACADE.getAllPersons())).build();
    }

    @GET
    @Path("/{phoneNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonInfoByPhone(@PathParam("phoneNumber") String phoneNumber) throws PhoneNotFoundException {

        return Response.ok().entity(GSON.toJson(FACADE.getPersonInfoByPhone(phoneNumber))).build();

    }

    @GET
    @Path("/hobby/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersonsWithHobby(@PathParam("hobby") String hobbyName) throws HobbyNotFoundException {

        return Response.ok().entity(GSON.toJson(FACADE.getPersonsWithHobby(hobbyName))).build();

    }

    @GET
    @Path("/city/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersonsFromCity(@PathParam("city") String city) throws CityInfoNotFoundException {

        return Response.ok().entity(GSON.toJson(FACADE.getPersonsFromCity(city))).build();

    }

    @GET
    @Path("/count/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonCountOfHobby(@PathParam("hobby") String hobbyName) throws HobbyNotFoundException {

        return Response.ok().entity(GSON.toJson(FACADE.getPersonCountOfHobby(hobbyName))).build();

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPerson(String content) {
        PersonDto p = GSON.fromJson(content, PersonDto.class);
        return Response.ok().entity(GSON.toJson(FACADE.addPerson(p))).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(String content) {
        PersonDto p = GSON.fromJson(content, PersonDto.class);
        return Response.ok().entity(GSON.toJson(FACADE.editPerson(p))).build();
    }

    @GET
    @Path("/pop")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String populate() {
        PersonDto first = new PersonDto("Jens", "Hansen", "test@test.dk", "Football", "Teamsport where you kick to a ball", "11111111", "Jens Hansens Phone", "Hjalmersgade", "22", "2tv", 2200, "Copenhagen");
        PersonDto second = new PersonDto("Hanne", "Hansen", "test2@test.dk", "Handball", "Teamsport where you throw a ball", "66666666", "Hanne Hansens Phone", "Hjalmersgade", "22", "2tv", 2200, "Copenhagen");
        PersonDto third = new PersonDto("Bjarne", "Lund", "test3@test.dk", "Football", "Teamsport where you kick to a ball", "66233344", "Bjarne Lunds Phone", "Lortegade", "232", "3tv", 2200, "Copenhagen");
        PersonDto fourth = new PersonDto("Hans", "Berg", "test4@test.dk", "Volleyball", "Teamsport with a ball", "22222222", "Hans Bergs Phone", "Jomfruegade", "2", "1tv", 8210, "Aarhus");
        PersonDto fifth = new PersonDto("Ken", "Nielsen", "test5@test.dk", "Cycling", "Cardio Training", "33333333", "Ken Nielsens Phone", "Abegade", "99", "22mdtf", 9210, "Aalborg");
        PersonDto sixth = new PersonDto("Julie", "Olsen", "test6@test.dk", "Shopping", "Spending money", "44444444", "Julie Olsens Phone", "Borupsvej", "18", "5th", 2300, "Copenhagen");
        
        
        first.getHobbies().add(new HobbyDto(new Hobby("Curling", "Boring game")));
        second.getHobbies().add(new HobbyDto(new Hobby("Running", "Cardio training")));
//        sixth.getPhones().add(new PhoneDto(new Phone("99999999", "Julie Olsens work phone")));
        FACADE.addPerson(first);
        FACADE.addPerson(second);
        FACADE.addPerson(third);
        FACADE.addPerson(fourth);
        FACADE.addPerson(fifth);
        FACADE.addPerson(sixth);
        
        return "Success!";
    }

}
