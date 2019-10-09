package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDto;
import facades.BusinessFacade;
import facades.PersonFacade;
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
@Path("Person")
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
    @Path("/All")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons(@PathParam("number") String number) {
        return Response.ok().entity(GSON.toJson(FACADE.getAllPersons())).build();
    }

    @GET
    @Path("/Email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonByEmail(@PathParam("email") String email) {
        return Response.ok().entity(GSON.toJson(FACADE.getPersonByEmail(email))).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void createPerson(String content) {
        PersonDto p = GSON.fromJson(content, PersonDto.class);
        FACADE.addPerson(p);
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePerson(String content) {
        PersonDto p = GSON.fromJson(content, PersonDto.class);
        FACADE.editPerson(p);
    }

}
