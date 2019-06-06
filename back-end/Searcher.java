package edu.ucr.cs172.project.partB;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.AbstractMap;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Searcher 
{
   private IndexSearcher indexSearcher;
   private DirectoryReader indexReader;;
   private Directory indexDirectory;

   private Analyzer analyzer;
   private MultiFieldQueryParser parser;
   //private boolean isParserInitialized;

   private Map<String,Float> scoreWeights;
   private final String[] FIELDS = {"title", "content"};

   private List<Document> rankedDocuments;
   private List<String> queryTerms;

   // Paramater 1: String - File path to directory of indexes
   // Parameter 2: float - weight for results found in the title of the document
   // Parameter 3: float - weight for results found in the body of the document
   public Searcher(String indexDirectoryPath, float titleWeight, float contentWeight) throws IOException 
   {
      //this directory will contain the indexes
      indexDirectory = FSDirectory.open(new File(indexDirectoryPath).toPath());

      indexReader = DirectoryReader.open(indexDirectory);
      indexSearcher = new IndexSearcher(indexReader);
      scoreWeights = new HashMap<>();
      //isParserInitialized = false;

      scoreWeights.put("title", titleWeight);
      scoreWeights.put("content", contentWeight);

      analyzer = new StandardAnalyzer();
      parser = new MultiFieldQueryParser(FIELDS, analyzer, scoreWeights);
   }

   public List<Document> search(String queryString, int numberOfTopResults) throws IOException, ParseException
   {
      Query query = parser.parse(queryString);

      analyze(queryString);

      ScoreDoc[] hits = indexSearcher.search(query, numberOfTopResults).scoreDocs;

      rankedDocuments = new ArrayList<Document>();

      for (int rank = 0; rank < hits.length; ++rank) {
         Document hitDoc = indexSearcher.doc(hits[rank].doc);
      
         rankedDocuments.add(hitDoc);
      }

      return rankedDocuments;
   }

   public void analyze(String text) throws IOException{
      queryTerms = new ArrayList<String>();
      TokenStream tokenStream = analyzer.tokenStream("query", text);
      CharTermAttribute attr = tokenStream.addAttribute(CharTermAttribute.class);
      tokenStream.reset();
      while(tokenStream.incrementToken()) 
      {
         queryTerms.add(attr.toString());
      }        
   }

   public List<String> getQueryTermList()
   {
      return queryTerms;
   }

   public Map.Entry<Integer,Integer> getSnipetMap(int rank)
   {
      for(int i = 0; i < queryTerms.size(); i++)
      {
         int start = 0;
         int end = 0;
         int middleIndex = -1;

         List<Integer> termIndexes = getTermIndexes(rank, i);

         if(termIndexes.size() > 1)
         {
            middleIndex = termIndexes.get(termIndexes.size() / 2);
         }
         else
         {
            if(termIndexes.size() == 1)
            {
               middleIndex = termIndexes.get(1);
            }
         }

         if(middleIndex != -1)
         {
            if(middleIndex > 179)
            {
               start = middleIndex - 180;
            }
            else
            {
               start = 0;
            }

            if( (middleIndex + 180) < rankedDocuments.get(rank).get("content").length())
            {
               end = middleIndex + 180;
            }
            else
            {
               end = rankedDocuments.get(rank).get("content").length() - 1;
            }

            return new AbstractMap.SimpleEntry<Integer,Integer>(start, end);
         }
      }

      return new AbstractMap.SimpleEntry<Integer,Integer>(0,0);
   }

   public void close() throws IOException
   {
      indexReader.close();
      indexDirectory.close();
   }

   private List<Integer> getTermIndexes(int rank, int queryTermIndex)
   {
      int lastIndex = 0;

      List<Integer> termIndexes = new ArrayList<Integer>();

      while(lastIndex != -1)
      {
         lastIndex = rankedDocuments.get(rank).get("content").toLowerCase().indexOf(queryTerms.get(queryTermIndex), lastIndex);

         if(lastIndex != -1)
         {
            termIndexes.add(lastIndex);
            if(lastIndex < (rankedDocuments.get(rank).get("content").length() - 1) )
            {
               lastIndex += 1;
            }
            else
            {
               lastIndex = -1;
            }
         }
      }

      return termIndexes;
   }

   /*
   public void setTitleWeight(float weight)
   {
      scoreWeights.put("title", weight);
   }

   public void setContentWeight(float weight)
   {
      scoreWeights.put("content", weight);
   }

   public void initializeParser
   {
      parse = new MultiFieldQueryParser(fields, analyzer, boosts);
      isParserInitialized = true;
   }
   */

}