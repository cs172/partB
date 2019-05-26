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
        return htmlDocument.body().text();
    }

    public String body()
    {
        return htmlDocument.body().text();
    }
}