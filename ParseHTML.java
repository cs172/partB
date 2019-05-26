package edu.ucr.cs172.project.partB;

import java.io.File;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ParseHTML
{
    Document htmlDocument;

    public ParseHTML(File htmlFile)
    {
        htmlDocument = Jsoup.parse(htmlFile, "UTF-8", "");
    }

    public void updateFile(File htmlFile)
    {
        htmlDocument = Jsoup.parse(htmlFile, "UTF-8", "");
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