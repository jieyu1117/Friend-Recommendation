/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.assignment;

/**
 *
 * @author yjie5
 */
import java.util.*;

//V is an object
public interface Graph<V> {
    // Returns the number of vertices in the graph
    int getSize();
    
    // Returns the vertices in the graph
    List<V> getVertices();
    
    // Returns the vertex object for the specified index
    V getVertex(int index);
    
    // Returns the index for the specified vertex
    int getIndex(V v);
    
    // Returns the neighbors of vertex with the specified index
    List<Integer> getNeighbors(int index);
    
    // Returns the degree for a specified vertex index
    int getDegree(int index);
    
    // Prints the edges
    void printEdges();
    
    // Clears the graph
    void clear();
    
    // Returns true if v is added to the graph. Returns false if v is already in the graph
    boolean addVertex(V v);
    
    // Adds an edge from u to v to the graph throws IllegalArgumentException if u or v is invalid
    // Returns true if the edge is added and false if (u, v) is already in the graph
    boolean addEdge(int u, int v);
    
    // Obtains a breadth-first search tree starting from v
    AbstractGraph<V>.Tree bfs(int v);  
}
