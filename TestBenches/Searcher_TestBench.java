package edu.ucr.cs172.project.partB;

import java.io.IOException;
import java.util.List;
import java.util.Map;
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

            List<Document> documents = searcher.search(args[0], 10);

            for (int rank = 0; rank < documents.size(); ++rank) {
                Map.Entry<Integer,Integer> snipetIndexMap = searcher.getSnipetMap(rank);                
                System.out.println("Document Ranked:  " + (rank + 1) + " - " 
                    + documents.get(rank).get("file_path")  + '\n' + '\n'
                    + documents.get(rank).get("title") + '\n' + '\n' 
                    + snipetIndexMap.getKey() + " " + snipetIndexMap.getValue() + '\n' + '\n'
                    + documents.get(rank).get("content").substring(snipetIndexMap.getKey(), snipetIndexMap.getValue()) + '\n' + '\n');
            }

            searcher.close();
        }
        catch(IOException e1){e1.printStackTrace();}
        catch(ParseException e2){e2.printStackTrace();}
    }
}