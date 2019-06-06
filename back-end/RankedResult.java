package edu.ucr.cs172.project.partB;

public class RankedResult
{
    private int rank;
    private String title;
    private String snippet;
    private String documentPath;

    public RankedResult(int rank, String title, String snippet, String documentPath)
    {
        this.rank = rank;
        this.title = title;
        this.snippet = snippet;
        this.documentPath = documentPath;
    }

    public int getRank()
    {
        return rank;
    }

    public String getTitle()
    {
        return title;
    }

    public String getSnippet()
    {
        return snippet;
    }

    public String getDocumentPath()
    {
        return documentPath;
    }
}