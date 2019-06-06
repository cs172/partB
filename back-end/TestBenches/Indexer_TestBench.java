package edu.ucr.cs172.project.partB;

import java.io.IOException;

public class Indexer_TestBench
{
    public static void main(String[] args)
    {
        try
        {
            int numberOfIndexes;
            Indexer indexer = new Indexer("../indexes/");
            indexer.createIndex("/home/rblaz001/Documents/cs172project/project/storage/");
            indexer.close();
        }
        catch(IOException e1){e1.printStackTrace();}
    }
}