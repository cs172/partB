package edu.ucr.cs172.project.partB;

import java.io.IOException;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearcherApplication 
{
    public static void main(String[] args) 
        throws IOException, ParseException
    {   
        SpringApplication.run(SearcherApplication.class, args);
    }
}