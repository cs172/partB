// Nhat Le - UCR

package edu.ucr.cs.nle020.lucenesearcher;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ArticleController {
    static List<Article> articles;
    static {
        articles = new ArrayList<>();
        articles.add(new Article(1, "First Article",
                "Class Overview, Overview of Information Retrieval and Search Engines"));
        articles.add(new Article(2, "Second Article",
                "Ranking: Vector space model, Probabilistic Model, Language model"));
        articles.add(new Article(3, "Third Article",
                "Web Search: Spam, topic-specific pagerank"));
    }

    @GetMapping("/articles")
    public List<Article> searchArticles(
            @RequestParam(required=false, defaultValue="") String query) {
        if (query.isEmpty())
            return articles;

        List<Article> matches = new ArrayList<>();
        for (Article article : articles) {
            if (article.body.contains(query))
                matches.add(article);
        }
        return matches;
    }
}
