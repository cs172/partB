package edu.ucr.cs172.project.partB;

import java.io.File;
import java.io.IOException;

public class ParseHTML_TestBench
{
    public static void main(String[] args)
    {
        File htmlFile = new File("/home/rblaz001/Documents/cs172project/project/storage/484453108.html");

        ParseHTML htmlParser = new ParseHTML(htmlFile);

        System.out.println( htmlParser.title() + '\n' + '\n' + '\n');  
        System.out.println( htmlParser.body() );
    }
}