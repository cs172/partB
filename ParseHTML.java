package edu.ucr.cs172.project.partB;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ParseHTML
{
    // Requires error checking but it is a low priority at the moment.
    // All files passed will be html files obtained from our java crawler
    Document htmlDocument;

    public ParseHTML()
    {
        htmlDocument = new Document("");
    }

    public ParseHTML(File htmlFile)
    {
        updateFile(htmlFile);
    }

    public void updateFile(File htmlFile)
    {
        try
        {
            htmlDocument = Jsoup.parse(htmlFile, "UTF-8", "");
        }
        catch(IOException e){ e.printStackTrace(); }
    }

    public String title()
    {
        String temp = htmlDocument.title();

        if(temp != null)
        {
            return temp;
        }
        else
        {
            return "";
        }
    }

    public String body()
    {
        if(htmlDocument.body() != null)
        {    
            return htmlDocument.body().text();
        }
        else
        {
            return "";
        }
    }
}