package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDto;
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
    public Response getAllPersons(@PathParam("number") String number) {
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
    public Response getAllPersonsWithHobby(@PathParam("hobby") String hobbyName) throws HobbyNotFoundException{
        
            return Response.ok().entity(GSON.toJson(FACADE.getPersonsWithHobby(hobbyName))).build();
        
    }
    
    @GET
    @Path("/city/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersonsFromCity(@PathParam("city") String city) throws CityInfoNotFoundException{
        
            return Response.ok().entity(GSON.toJson(FACADE.getPersonsFromCity(city))).build();
      
    }
    
    @GET
    @Path("/count/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonCountOfHobby(@PathParam("hobby") String hobbyName) throws HobbyNotFoundException{
        
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

}
