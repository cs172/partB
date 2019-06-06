package edu.ucr.cs172.project.partB;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;

public class SearchInterface
{
    private int rank;
    private String title;
    private String snippet;
    private String documentPath;
    private List<RankedResult> rankedResults;

    public static List<RankedResult> search(String query, int displayCount)
    {
        try
        {
            Searcher searcher = new Searcher("../indexes/", 1.5f, 1.0f);

            List<Document> documents = searcher.search(query, displayCount);

            for (int i = 0; i < documents.size(); ++i) {
                Map.Entry<Integer,Integer> snipetIndexMap = searcher.getSnipetMap(rank);                
                
                rank = i+1;
                title = documents.get(i).get("title");
                snippet = documents.get(i).get("content").substring(snipetIndexMap.getKey(), snipetIndexMap.getValue());
                documentPath = documents.get(rank).get("file_path");

                RankedResult result = new RankedResult(rank, title, sinppet, documentPath);
                rankedResults.add(result);
            }

            searcher.close();

            return rankedResults;
        }
        catch(IOException e1){e1.printStackTrace(); return new ArrayList<RankedResult>();}
        catch(ParseException e2){e2.printStackTrace(); return new ArrayList<RankedResult>(); }
    }
}