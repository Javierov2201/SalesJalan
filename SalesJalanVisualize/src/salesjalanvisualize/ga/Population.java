/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salesjalanvisualize.ga;

/**
 *
 * @author rfachrur
 */
public class Population {
    
    private Tour[] tours;
    
    // Building the population
    public Population(int populSize, boolean initialize) {
        tours = new Tour[populSize];
        if (initialize) {
            // Loop to create individuals
            for (int i = 0; i < populSize(); i++) {
                Tour newTour = new Tour();
                newTour.generateIndividu();
                saveTour(i, newTour);
            }
        }
    }
    
    public void saveTour(int index, Tour tour) { tours[index] = tour; }

    public Tour getTour(int index) { return tours[index]; }
    
    public Tour getFittest() {
        Tour fittest = tours[0];
        for (int i = 1; i < populSize(); i++) {
            if (fittest.getFitness() <= getTour(i).getFitness()) {
                fittest = getTour(i);
            }
        }
        return fittest;
    }
    
    public int populSize() { return tours.length; }
    
}
