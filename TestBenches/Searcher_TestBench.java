package edu.ucr.cs172.project.partB;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;

public class Searcher_TestBench
{
    public static void main(String[] args)
    {
        try
        {
            Searcher searcher = new Searcher("../indexes/", 1.5f, 1.0f);

            List<Document> documents = searcher.search("president", 10);

            for (int rank = 0; rank < documents.size(); ++rank) {
                System.out.println((rank + 1) + documents.get(rank).get("title") + " - " + documents.get(rank).get("content"));
            }

            searcher.close();
        }
        catch(IOException e1){e1.printStackTrace();}
        catch(ParseException e2){e2.printStackTrace();}
    }
}