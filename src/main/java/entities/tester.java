/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.Persistence;

/**
 *
 * @author Dennis
 */
public class tester {
    public static void main(String[] args) {
        Persistence.generateSchema("pu", null);
    }
}
