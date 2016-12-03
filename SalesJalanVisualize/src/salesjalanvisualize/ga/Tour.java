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
        tour.set(tourPosition, kota);
        fitness = 0;
        distance = 0;
    }
    
    // get the tours fitness from its distance
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1 / (double) getDistance();
        }
        return fitness;
    }
    
    // get total distance of the tour from all cities
    public int getDistance() {
        if (distance == 0) {
            int tourDistance = 0;
            for (int kotaIndex = 0; kotaIndex < tourSize(); kotaIndex++) {
                Kota fromKota = getKota(kotaIndex);
                Kota destinationKota;
                if (kotaIndex + 1 < tourSize()) {
                    destinationKota = getKota(kotaIndex + 1);
                } else {
                    destinationKota = getKota(0);
                }
                tourDistance += fromKota.distanceTo(destinationKota);
            }
            distance = tourDistance;
        }
        return distance;
    }
    
    // get amount of cities on the tour
    public int tourSize() { return tour.size(); }
    
    // to check if the tour contains a city
    public boolean containsKota(Kota kota) { return tour.contains(kota); }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.fitness) ^ (Double.doubleToLongBits(this.fitness) >> 32));
        hash = 37 * hash + this.distance;
        return hash;
    }
    
     @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tour other = (Tour) obj;
        if (Double.doubleToLongBits(this.fitness) != Double.doubleToLongBits(other.fitness)) {
            return false;
        }
//        if (this.distance != other.distance) {
//            return false;
//        }
//        return true;
        return this.distance == other.distance;
    }

    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < tourSize(); i++) {
            geneString += getKota(i) + "|";
        }
        return geneString;
    }

    @Override
    public int compareTo(Tour o) {
        if (o == null) {
            return -1;
        }
        return (int) (this.distance - o.distance);
    }
    
}
