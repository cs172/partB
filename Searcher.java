package edu.ucr.cs172.project.partB;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
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
import java.util.List;
import java.util.ArrayList;

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
      ScoreDoc[] hits = indexSearcher.search(query, numberOfTopResults).scoreDocs;

      List<Document> rankedDocuments = new ArrayList<Document>();

      for (int rank = 0; rank < hits.length; ++rank) {
         Document hitDoc = indexSearcher.doc(hits[rank].doc);
      
         rankedDocuments.add(hitDoc);
      }

      return rankedDocuments;
   }

   public void close() throws IOException
   {
      indexReader.close();
      indexDirectory.close();
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