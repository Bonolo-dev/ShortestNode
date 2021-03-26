/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.dto.ShortestPathDto;
import com.example.entity.Nodes;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author omphilebonolomonale
 */
@Service
public class NodeService {

    public NodeService() {
    }
    
    public ShortestPathDto ShortestPathPayloadBuilder(Nodes node) throws IOException{
        
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<String>> listType = new TypeReference<List<String>>() {};
        
        List<String> list = mapper.readValue(node.getShortestPath(), listType);
        
        return new ShortestPathDto(node.getName(),node.getDistance(),list);
        
    }
    
}
