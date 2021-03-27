/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.utility;

import com.example.models.Graph;
import com.example.models.Node;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;


/**
 *
 * @author omphilebonolomonale
 */
@Component
public class GenerateGraph {

    private Graph graph = new Graph();
    
    
    private Node nodeZero;
    
    public GenerateGraph(Map<Integer, List<String>> data){
     
        //Temporary holder of nodes
        List<Node> listOfNodes = new ArrayList<Node>();
        
        List<Node> finalNodes = new ArrayList<Node>();
        
        //First row of data contains the names of the nodes. We create Node object from here
        data.get(0).forEach(nodeNames->{
            
            listOfNodes.add(new Node(nodeNames));
            
        });
        data.remove(0);
        
        //Assuming the first node is Node zero
        nodeZero = listOfNodes.get(0);
        
        //Find adjacent nodes to a reference node
        data.forEach((k,v)->{
            
            //Get out of lambda function because list size is data.size()-1
            if(k==data.size()-1){
                return;
            }
            
            Node refNode = listOfNodes.get(k-1);
            
            if(k==0){
                
            }
            
            for(int i=1;i<=data.size();i++){
                /*
                    I could have handled this better from how I receive the data to be type safe.
                */
                try{     
                    Double value =Double.parseDouble(v.get(i));    
                    Node nearestNode = listOfNodes.get(i-1);
                    
                    /*
                        I dont want to have to go change addDestination so I will just cast it.
                    */                    
                    refNode.addDestination(nearestNode, (int) Math.round(value));
                    
                    
                    
                }catch(Exception ex){
                
                    /*I know this is bad practice. I could have done a whole lot better to avoid this
                    scenario
                    */
                    
                    
                }
                
            }
            finalNodes.add(refNode);
            
        });
        
        //Assemble graph Object
        
        finalNodes.forEach(node->{
            
            System.out.println(node.getName()+" : "+node.getDistance() + " : "+node.getAdjacentNodes());
            graph.addNode(node);
        });
        
        
    }

    public GenerateGraph(){
        
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D"); 
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        nodeZero = nodeA;
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
    
    public Node getNodeZero(){
        return nodeZero;
    }
    
    
    
    
}
