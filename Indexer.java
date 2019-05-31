package edu.ucr.cs172.project.partB;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Indexer {

   private IndexWriter writer;
   private Directory indexDirectory;
   private ParseHTML htmlParser;

   public Indexer(String indexDirectoryPath) throws IOException 
   {
      htmlParser = new ParseHTML();
      Analyzer analyzer = new StandardAnalyzer();

      //this directory will contain the indexes
      indexDirectory = FSDirectory.open(new File(indexDirectoryPath).toPath());

      //create the indexer
      IndexWriterConfig config = new IndexWriterConfig(analyzer);
      writer = new IndexWriter(indexDirectory, config);
   }

   public void close() throws CorruptIndexException, IOException 
   {
      writer.close();
      indexDirectory.close();
   }

   private void indexFile(File file) throws IOException 
   {
      // The Print statement should be removed after debugging
      System.out.println("Indexing "+file.getCanonicalPath());
      Document document = new Document();
      htmlParser.updateFile(file);

      document.add(new TextField("title", htmlParser.title(), Field.Store.YES));
      document.add(new TextField("content", htmlParser.body(), Field.Store.YES));
      writer.addDocument(document);
   }

   public void createIndex(String dataDirPath) 
      throws IOException {
      //get all files in the data directory
      File[] files = new File(dataDirPath).listFiles();

      for (File file : files) {
         if(!file.isDirectory()
            && !file.isHidden()
            && file.exists()
            && file.canRead()
         ){
            indexFile(file);
         }
      }
   }
}