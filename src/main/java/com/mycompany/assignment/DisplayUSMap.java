/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment;

/**
 *
 * @author yjie5
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class DisplayUSMap extends Application {

    private static AbstractGraph<String> graph;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        Platform.setImplicitExit(false);
        primaryStage = stage;
        updateGraph(graph);

        primaryStage.setTitle("Friendship Network");
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            event.consume(); 
            primaryStage.hide();
        });
    }

    
    public static void showGraph(AbstractGraph<String> g) {
        graph = g;

        if (primaryStage == null) {
            new Thread(() -> Application.launch(DisplayUSMap.class)).start();
        } else {
            Platform.runLater(() -> updateGraph(graph));
        }
    }

    private static void updateGraph(AbstractGraph<String> g) {
        if (primaryStage == null) {
                
            return;
        }

        Pane pane = new Pane();

        double centerX = 400, centerY = 300;
        double radius = 200;
        int n = g.getSize();
        
        //draw the point and name
        for (int i = 0; i < n; i++) {
            double angle = 2 * Math.PI * i / n;
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);

            Circle c = new Circle(x, y, 20, Color.LIGHTBLUE);
            pane.getChildren().add(c);

            javafx.scene.text.Text t = new javafx.scene.text.Text(x - 10, y, g.getVertex(i));
            pane.getChildren().add(t);
            
            //draw edge
            for (AbstractGraph.Edge e : g.neighbors.get(i)) {
                int j = e.v;
                double angle2 = 2 * Math.PI * j / n;
                double x2 = centerX + radius * Math.cos(angle2);
                double y2 = centerY + radius * Math.sin(angle2);

                Line line = new Line(x, y, x2, y2);
                line.setStroke(Color.GRAY);
                pane.getChildren().add(line);
            }
        }

        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setScene(scene);
    }
}
