import { ArticleService } from './article.service';
import { Document } from './document';
import { Observable, Subject } from 'rxjs';

@Component({
  selector: 'app-article',
  templateUrl: './document.component.html',
  styleUrls: ['./document.component.css']
})
export class DocumentComponent implements OnInit {
  articles: Article[];
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