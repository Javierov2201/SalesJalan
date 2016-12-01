/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salesjalanvisualize.city;

import java.util.ArrayList;

/**
 *
 * @author rfachrur
 */
public class ManageKota {
    
    private static final ManageKota instance = new ManageKota();
    private final ArrayList<Kota> cities;
    
     public static ManageKota getInstance() { return instance; }

    private ManageKota() { cities = new ArrayList<>(); }
    
    // Holds the cities

    // To add the cities destination
    public void addKota(Kota kota) { cities.add(kota); }

    // Getting the city
    public Kota getKota(int index) { return cities.get(index); }

    // Get the number of destination cities
    public int numberOfKota() { return cities.size(); }
    
}
