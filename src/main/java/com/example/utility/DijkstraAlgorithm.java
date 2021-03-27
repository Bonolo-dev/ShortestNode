/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.utility;

import com.example.entity.Nodes;
import com.example.models.Graph;
import com.example.models.Node;
import com.example.repo.NodesRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author omphilebonolomonale
 */
@Component
public class DijkstraAlgorithm {
    
    @Autowired
    NodesRepo nodesRepo;
    
    public Graph calculateShortestPathFromSource(Graph graph, Node source) throws JsonProcessingException {
    
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Entry < Node, Integer> adjacencyPair:currentNode.getAdjacentNodes().entrySet()){

                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            
            List<Node> shortestPath = currentNode.getShortestPath();
            List<String> shortestPathNameOnly = new ArrayList<String>();
            
            shortestPath.forEach(path ->{
                shortestPathNameOnly.add(path.getName());
            });
            
            
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            
            nodesRepo.save(new Nodes(currentNode.getName()
                    ,currentNode.getDistance(),mapper.writeValueAsString(shortestPathNameOnly)));
            
            settledNodes.add(currentNode);
            
        }
        return graph;
    }
    
 
    private Node getLowestDistanceNode(Set < Node > unsettledNodes) {
    
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
    
 
    private void CalculateMinimumDistance(Node evaluationNode,Integer edgeWeigh, Node sourceNode) {
        
        Integer sourceDistance = sourceNode.getDistance();
        
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }

    }
}
