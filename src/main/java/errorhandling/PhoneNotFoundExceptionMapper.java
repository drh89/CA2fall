/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package errorhandling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static errorhandling.HobbyNotFoundExceptionMapper.gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author Dennis
 */
public class PhoneNotFoundExceptionMapper implements ExceptionMapper<PhoneNotFoundException> {
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Response toResponse(PhoneNotFoundException ex) {
        Logger.getLogger(PhoneNotFoundException.class.getName()).log(Level.SEVERE,null,ex);
        ExceptionDTO error = new ExceptionDTO(Response.Status.NOT_FOUND.getStatusCode(), ex.getMessage());
        return Response.status(404).entity(gson.toJson(error)).type(MediaType.APPLICATION_JSON).build();
    }
    
}
