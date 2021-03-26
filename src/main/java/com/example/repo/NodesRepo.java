/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.repo;

import com.example.entity.Nodes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author omphilebonolomonale
 */
@Repository
public interface NodesRepo extends CrudRepository<Nodes, Integer> {
    
//    Nodes findById (Integer id);
    Nodes findByName (String name);
    
    
}
