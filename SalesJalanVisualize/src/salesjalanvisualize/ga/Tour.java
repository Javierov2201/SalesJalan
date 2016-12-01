/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salesjalanvisualize.ga;

import java.util.ArrayList;
import java.util.Collections;
import salesjalanvisualize.city.Kota;
import salesjalanvisualize.city.ManageKota;

/**
 *
 * @author rfachrur
 */
public class Tour implements Comparable<Tour> {
    
    private ArrayList<Kota> tour = new ArrayList<>();
    private double fitness = 0;
    private int distance = 0;
    
    // Init an empty tour
    public Tour() {
        for (int i = 0; i < ManageKota.getInstance().numberOfKota(); i++) { tour.add(null); }
    }
    
    public Tour(ArrayList tour) { this.tour = tour; }
    
    public void generateIndividu() {
        for (int kotaIndex = 0; kotaIndex < ManageKota.getInstance().numberOfKota(); kotaIndex++) { 
            setKota(kotaIndex, ManageKota.getInstance().getKota(kotaIndex)); 
        }
            
        Collections.shuffle(tour);
    }
    
    public Kota getKota(int tourPosition) { return tour.get(tourPosition); }

    public void setKota(int tourPosition, Kota kota) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
