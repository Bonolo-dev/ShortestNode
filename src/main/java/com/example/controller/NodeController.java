/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controller;

import com.example.dto.ShortestPathDto;
import com.example.entity.Nodes;
import com.example.models.Graph;
import com.example.repo.NodesRepo;
import com.example.service.NodeService;
import com.example.utility.DijkstraAlgorithm;
import com.example.utility.GenerateGraph;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author omphilebonolomonale
 */
@RestController
@RequestMapping("/node")
public class NodeController {
    
    @Autowired
    NodesRepo nodesRepo;
    
    @Autowired
    NodeService nodeService;
    
    @GetMapping("/all")
    public ResponseEntity<?> getAllNodes() throws JsonProcessingException, IOException{
        
        List<Nodes> allNodes = (List)nodesRepo.findAll();
        List<ShortestPathDto> result = new ArrayList<>();
        for(Nodes node: allNodes){
            result.add(nodeService.ShortestPathPayloadBuilder(node));
        }
        
        return ResponseEntity.ok().body(result);
    }
    
    @GetMapping("/shortest/{nodeName}")
    public ResponseEntity<?> getShortestRoute(@PathVariable String nodeName) throws IOException{
        
        Nodes node = nodesRepo.findByName(nodeName);
        
        return ResponseEntity.ok().body(nodeService.ShortestPathPayloadBuilder(node));
        
    }
    
    
}
