package com.example;


import com.example.utility.DijkstraAlgorithm;
import com.example.utility.ExtractDataExcel;
import com.example.utility.GenerateGraph;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasicApplication implements ApplicationRunner{

    @Autowired
    ExtractDataExcel extractDataExcel;
    
    @Autowired
    DijkstraAlgorithm shortestPathAlgorithm;
	
    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);	
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        
        
        Map<Integer,List<String>>result = (extractDataExcel.ExtractData());
        
        GenerateGraph generatedGraph= new GenerateGraph(result);
        
        shortestPathAlgorithm.calculateShortestPathFromSource(generatedGraph.getGraph()
                ,generatedGraph.getNodeZero());
    }
}
