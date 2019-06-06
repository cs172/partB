package edu.ucr.cs172.project.partB; 

import java.util.List;
import java.io.IOException;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class SearchController
{
    @GetMapping("/search")
    public List<RankedResult> search(@RequestParam(name="query", required=true) String query,
                                     @RequestParam(name="displaycount",required=false, defaultValue="10") Integer displayCount)
        throws IOException, ParseException
    {
        return SearchInterface.search(query, displayCount);
    }
}