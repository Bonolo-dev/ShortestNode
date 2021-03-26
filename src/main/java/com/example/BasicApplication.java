package com.example;

import com.example.models.Graph;
import com.example.utility.DijkstraAlgorithm;
import com.example.utility.GenerateGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasicApplication implements ApplicationRunner{

    @Autowired
    DijkstraAlgorithm djk;
    
    @Autowired
    GenerateGraph generatedGraph;
	
    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);	
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        
        djk.calculateShortestPathFromSource(generatedGraph.getGraph(),generatedGraph.nodeA );
    }
}
