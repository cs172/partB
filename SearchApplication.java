package edu.ucr.cs172.project.partB; 

import java.io.IOException;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearchApplication 
{
    public static void main(String[] args) 
        throws IOException, ParseException
    {   
        SpringApplication.run(SearchApplication.class, args);
    }
}