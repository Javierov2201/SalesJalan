/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salesjalanvisualize;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import salesjalanvisualize.city.Kota;
import salesjalanvisualize.city.ManageKota;
import salesjalanvisualize.ga.GA;
import salesjalanvisualize.ga.Population;
import salesjalanvisualize.ga.Tour;

/**
 *
 * @author rfachrur
 */
public class SalesJalanVisualize extends Application {
    
    Tour bestTour;
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane mainRoot = new BorderPane();
        Pane root = new Pane();
        Label label = new Label();
        label.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(0, 100, 15, 100));
        mainRoot.setCenter(root);
        mainRoot.setBottom(label);
        
        Scene scene = new Scene(mainRoot, 600, 600);
        scene.setOnMouseClicked(event -> {
            Circle circle = new Circle(event.getSceneX(), event.getSceneY(), 10);
            ManageKota.getInstance().addKota(new Kota(event.getSceneX(), event.getSceneY()));
            root.getChildren().add(circle);
            bestTour = null;
            label.setText("Jumlah kota : " + ManageKota.getInstance().numberOfKota());
            
        });
        scene.setOnKeyTyped((KeyEvent event) -> {
            // Init population
            
            Population pop = new Population(50, true);
            System.out.println("Initial distance : " + pop.getFittest().getDistance());
            
            // Evolving population for 100 generations
            pop = GA.initPopulation(pop);
            for (int i = 0; i < 100; i++) {
                pop = GA.initPopulation(pop);
            }
            
            Tour tour = pop.getFittest();
            if (tour.compareTo(bestTour) < 0) {
                bestTour = tour;
                label.setText("Jumlah Kota: " + ManageKota.getInstance().numberOfKota()+ " | Total Jarak: " + pop.getFittest().getDistance());
                root.getChildren().removeIf((Node t) -> {
                    return t.getClass().getSimpleName().equals("Line");
                });
                for (int i = 0; i < tour.tourSize() - 1; i++) {
                    Line line = new Line(tour.getKota(i).getLocation().getX(), tour.getKota(i).getLocation().getY(),
                            tour.getKota(i + 1).getLocation().getX(), tour.getKota(i + 1).getLocation().getY());
                    line.setStroke(Color.RED);
                    line.setStrokeWidth(5);
                    
                    root.getChildren().add(line);
                    line.toBack();
                }
            }
        });
        primaryStage.setTitle("Sales Jalan-Jalan");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }

    
}
