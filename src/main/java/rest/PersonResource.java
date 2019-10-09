package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDto;
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

/**
 *
 * @author Ryge
 */
@Path("Person")
public class PersonResource {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu", null);
    PersonFacade fPerson = new PersonFacade(emf);

    @GET
    @Path("/All")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons(@PathParam("number") String number) {
        return Response.ok().entity(gson.toJson(fPerson.getAllPersons())).build();
    }

    @GET
    @Path("/Email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonByEmail(@PathParam("email") String email) {
        return Response.ok().entity(gson.toJson(fPerson.getPersonByEmail(email))).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void createPerson(String content) {
        PersonDto p = gson.fromJson(content, PersonDto.class);
        fPerson.addPerson(p);
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePerson(String content) {
        PersonDto p = gson.fromJson(content, PersonDto.class);
        fPerson.editPerson(p);
    }

}
