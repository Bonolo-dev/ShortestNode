/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dto;

import java.util.List;

/**
 *
 * @author omphilebonolomonale
 */
public class ShortestPathDto {
    
    private String nodeName;
    private int nodeDistance;
    private List<String> path;

    public ShortestPathDto() {
    }
    
    

    public ShortestPathDto(String nodeName, int nodeDistance, List<String> path) {
        this.nodeName = nodeName;
        this.nodeDistance = nodeDistance;
        this.path = path;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public int getNodeDistance() {
        return nodeDistance;
    }

    public void setNodeDistance(int nodeDistance) {
        this.nodeDistance = nodeDistance;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ShortestPathDto{" + "nodeName=" + nodeName + ", nodeDistance=" + nodeDistance + ", path=" + path + '}';
    }
    
    
    
    
    
}
