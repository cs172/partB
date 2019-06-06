import { Component, OnInit } from '@angular/core';

import { ArticleService } from './article.service';
import { Article } from './article';
import { Observable, Subject } from 'rxjs';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css']
})
export class ArticleComponent implements OnInit {
  articles: Article[];
  title='Enter your Query'
  lastSearch: string;

  constructor(private articleService: ArticleService) { }

  ngOnInit() {    
  }

  search(query: string) {
    this.lastSearch = query;

    this.articleService.getArticles(query)
        .subscribe(articles => this.articles = articles);
  }
}
