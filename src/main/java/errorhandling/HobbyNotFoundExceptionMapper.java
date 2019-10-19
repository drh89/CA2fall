/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package errorhandling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static errorhandling.CityInfoNotFoundExceptionMapper.gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Dennis
 */
@Provider
public class HobbyNotFoundExceptionMapper implements ExceptionMapper<HobbyNotFoundException> {
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Response toResponse(HobbyNotFoundException ex) {
        Logger.getLogger(HobbyNotFoundException.class.getName()).log(Level.SEVERE,null,ex);
        ExceptionDTO error = new ExceptionDTO(Response.Status.NOT_FOUND.getStatusCode(), ex.getMessage());
        return Response.status(404).entity(gson.toJson(error)).type(MediaType.APPLICATION_JSON).build();
    }
    
}
