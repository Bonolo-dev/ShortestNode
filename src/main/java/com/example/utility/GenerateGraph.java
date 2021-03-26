/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.utility;

import com.example.models.Graph;
import com.example.models.Node;
import org.springframework.stereotype.Component;


/**
 *
 * @author omphilebonolomonale
 */
@Component
public class GenerateGraph {
    
        
    public Node nodeA = new Node("A");
    public Node nodeB = new Node("B");
    public Node nodeC = new Node("C");
    public Node nodeD = new Node("D"); 
    public Node nodeE = new Node("E");
    public Node nodeF = new Node("F");
    
    private Graph graph = new Graph();

    public GenerateGraph(){

        nodeA.addDestination(nodeB, 10);
        nodeA.addDestination(nodeC, 15);
        nodeB.addDestination(nodeD, 12);
        nodeB.addDestination(nodeF, 15);
        nodeC.addDestination(nodeE, 10);
        nodeD.addDestination(nodeE, 2);
        nodeD.addDestination(nodeF, 1);
        nodeF.addDestination(nodeE, 5);

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);
        
        }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }
    
    
}
