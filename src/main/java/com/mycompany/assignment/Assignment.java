/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.assignment;

/**
 *
 * @author yjie5
 */
import java.util.*;
import javafx.application.Platform;

public class Assignment {

    private static AbstractGraph<String> graph = new UnweightedGraph<>();
    //graph is a variable name
    //String is datatype -> each vertices is string(eg. Alice, Bob)
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("\n==================================================");
        System.out.println("!!!Welcome to Social Friend Recommendation System!!!");
        System.out.println("==================================================");

        int mainChoice;
        while(true){
            System.out.println("\n============================================");
            System.out.println("                 Main Menu                  ");
            System.out.println("============================================");
            System.out.println("|| 1. Create Friendship Network           ||");
            System.out.println("|| 2. Search for a Friend (BFS)           ||");
            System.out.println("|| 3. View the Friendship Network         ||");
            System.out.println("|| 4. Recommend Friends                   ||");
            System.out.println("|| 0. Exit                                ||");
            System.out.println("============================================");
            System.out.print("\nSelection: ");
            try{
                mainChoice = sc.nextInt();
            
                sc.nextLine(); // newline

                switch (mainChoice) {
                    case 1:
                        createGraphMenu();
                        break;
                    case 2:
                        searchFriend();
                        break;
                    case 3:
                        displayGraph();
                        break;
                    case 4:
                        recommendFriend();
                        break;
                    case 0:
                        System.out.println("Exiting program.");
                        Platform.exit(); //exit javaFX
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter 1/2/3/4/0 only!");
                }
            }catch(Exception e){
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
            }
            
        }
    }

    // Sub-menu for adding/removing vertices and edges
    private static void createGraphMenu() {
        int subChoice;
        while(true) {
            System.out.println("\n===== Create/Modify Friendship Network =====");
            System.out.println("1. Add friend (Vertex)");
            System.out.println("2. Remove friend (Vertex)");
            System.out.println("3. Add friendship (Edge)");
            System.out.println("4. Remove friendship (Edge)");
            System.out.println("0. Return to Main Menu");
            System.out.print("Selection: ");
            try{
                subChoice = sc.nextInt();
                sc.nextLine();

                switch (subChoice) {
                    case 1:
                        int addFriend;
                        while (true) {
                            System.out.println("-".repeat(20) + "Adding Vertex" + "-".repeat(20));
                            System.out.print("How many friends to add? : ");
                            try {
                                addFriend = sc.nextInt();
                                sc.nextLine();
                                if(addFriend > 0){
                                    break;
                                }else{
                                    System.out.println("Please enter positive number!");
                                }
                            }catch(Exception e){
                                System.out.println("Invalid input! Please enter a number.");
                                sc.nextLine();
                            }
                        }

                        for (int i = 0; i < addFriend; i++) {
                            System.out.print("Enter name for friend " + (i + 1) + ": ");
                            String name = sc.nextLine();

                            if(name.isEmpty()){
                                System.out.println("Name cannot be empty.");
                                i--;
                                continue;
                            }
                            if (graph.addVertex(name)) {
                                System.out.println("Friend added.");
                            } else {
                                System.out.println("Friend already exists.");
                                i--;
                            }
                        }
                        break;
                    case 2:
                        System.out.println("-".repeat(20) + "Removing Vertex" + "-".repeat(20));
                        System.out.print("Enter friend name to remove: ");
                        String removeName = sc.nextLine();
                        
                        if(removeName.isEmpty()){
                            System.out.println("Name cannot be empty.");
                            break;
                        }
                        
                        int idx = graph.getIndex(removeName);
                        //-1 is because on the AbstractGraph, the index of vertices(start from 0) is store the friend name
                        if (idx != -1) { //index =0/1/2/3....
                            removeVertex(idx);
                            System.out.println("Friend removed.");//so can successfully delete the vertex
                        } else {
                            System.out.println("Friend not found.");
                        }
                        break;

                    case 3:
                        //Add edge
                        System.out.println("-".repeat(20) + "Adding Edges" + "-".repeat(20));
                        
                        System.out.print("Enter first friend name: ");
                        String friend1 = sc.nextLine();
                        
                        if(friend1.isEmpty()){
                            System.out.println("Name cannot be empty.");
                        }
                        
                        System.out.print("Enter second friend name: ");
                        String friend2 = sc.nextLine();
                        
                        if(friend2.isEmpty()){
                            System.out.println("Name cannot be empty.");
                        }
                        
                        if(friend1.equals(friend2)){
                            System.out.println("A friend cannot be connected to themselves.");
                            return;
                        }
                        
                        int index1 = graph.getIndex(friend1);
                        int index2 = graph.getIndex(friend2);

                        if (index1 != -1 && index2 != -1) {
                            if (graph.addEdge(index1, index2)) {
                                System.out.println("Friendship added.");
                            } else {
                                System.out.println("Friendship already exists.");
                            }
                        } else {
                            System.out.println("One or both friends not found.");
                        }
                        break;
                        
                    case 4:
                        System.out.println("-".repeat(20) + "Removing Edges" + "-".repeat(20));
                        System.out.print("Enter first friend name: ");
                        String fr1 = sc.nextLine();
                        
                        if(fr1.isEmpty()){
                            System.out.println("Name cannot be empty.");
                        }
                        
                        System.out.print("Enter second friend name: ");
                        String fr2 = sc.nextLine();
                        
                        if(fr2.isEmpty()){
                            System.out.println("Name cannot be empty.");
                        }
                        
                        int removeIndex1 = graph.getIndex(fr1);
                        int removeIndex2 = graph.getIndex(fr2);
                        if (removeIndex1 != -1 && removeIndex2 != -1) {
                            if (removeEdge(removeIndex1, removeIndex2)) {
                                System.out.println("Friendship removed.");
                            } else {
                                System.out.println("Friendship not found.");
                            }
                        } else {
                            System.out.println("One or both friends not found.");
                        }
                        break;
                    case 0:
                        System.out.println("Returning to main menu.");
                        return;
                    default:
                        System.out.println("Invalid input! Please enter 1/2/3/4/0 only!");
                }
            }catch(Exception e){
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
            }
            
        }
    }

    // BFS search
    private static void searchFriend() {
        System.out.println("-".repeat(20) + "Search Friend by using BFS" + "-".repeat(20));
        System.out.print("Enter starting friend name: ");
        String startFr = sc.nextLine();

        if (startFr.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        int startIndex = graph.getIndex(startFr);

        if (startIndex == -1) {
            System.out.println("Friend not found.");
            return;
        }
        
        //use to avoid the same friend visit duplicate
        AbstractGraph<String>.Tree tree = graph.bfs(startIndex);
        
        System.out.println("BFS traversal starting from " + startFr + ":");
        
        for (int index : tree.getSearchOrder()) {
            System.out.print(graph.getVertex(index) + " ");
        }
        
        System.out.println(); // new line after traversal
    }


    // Remove vertex and all edges associated, and fix indices
    private static void removeVertex(int index) {
        graph.getVertices().remove(index); //remove the vertices
        
        graph.neighbors.remove(index); //remove the vertice from neighbors list
        
        //remove all edges that have connected with this vertex
        for (List<AbstractGraph.Edge> edges : graph.neighbors) {
            edges.removeIf(edge1 -> edge1.v == index); //iterator
            
            //update the new index for each vertex
            for (AbstractGraph.Edge edge1 : edges) {
                if (edge1.v > index) {
                    edge1.v--;
                }
                
                if (edge1.u > index) {
                    edge1.u--;
                }
                //use -- is because we already remove a vertex now the index or the vertex will be move infront
            }
        }
    }

    // Remove edge between u and v
    private static boolean removeEdge(int u, int v) {
        List<AbstractGraph.Edge> edges = graph.neighbors.get(u);
        boolean removed = edges.removeIf(e -> e.v == v);
        edges = graph.neighbors.get(v);
        removed |= edges.removeIf(e -> e.v == u);
        return removed;
    }

    // Display graph in JavaFX
    private static void displayGraph() {
        DisplayUSMap.showGraph(graph);
    }

    private static void recommendFriend() {
        System.out.println("-".repeat(20) + "Recommend Friends" + "-".repeat(20));
        System.out.print("Enter friend name for recommendation: ");
        String fr_name = sc.nextLine();
        
        if(fr_name.isEmpty()){
            System.out.println("Name cannot be empty.");
        }
        
        int index = graph.getIndex(fr_name);
        
        if (index == -1) {
            System.out.println("Friend not found.");
            return;
        }

        //current friend
        Set<Integer> directFriends = new HashSet<>();
        for (AbstractGraph.Edge edge : graph.neighbors.get(index)) {
            directFriends.add(edge.v);
        }

        Map<String, List<String>> recommendationMap = new HashMap<>();

        for (int friendIndex : directFriends) {
            String mutualName = graph.getVertex(friendIndex);
            for (AbstractGraph.Edge edge : graph.neighbors.get(friendIndex)) {
                int potential = edge.v;

                if (potential == index || directFriends.contains(potential)) {
                    continue;
                }

                String potentialName = graph.getVertex(potential);
                recommendationMap
                        .computeIfAbsent(potentialName, k -> new ArrayList<>())
                        .add(mutualName);
            }
        }

        if (recommendationMap.isEmpty()) {
            System.out.println("No friend recommendations available.");
        } else {
            System.out.println("Recommended friends for " + fr_name + ":");
            recommendationMap.entrySet()
                    .stream()
                    .sorted((a, b) -> b.getValue().size() - a.getValue().size())
                    .forEach(entry -> {
                        System.out.println(entry.getKey()
                                + " (mutual friends: " + entry.getValue().size()
                                + ") -> " + entry.getValue());
                    });
        }
    }
}
