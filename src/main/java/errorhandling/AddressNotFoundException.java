/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package errorhandling;

/**
 *
 * @author Dennis
 */
public class AddressNotFoundException extends Exception {
    
    public AddressNotFoundException(String message){
        super(message);
    }
    
}
