
import { Injectable } from '@angular/core';
import { Article } from './article';
// import { Headers, Http, Response } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class ArticleService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getArticles(query: string, displayCount: number):  Observable<Article[]> {
    //displayCount=5;
    console.log(this.baseUrl + '/api/articles?query=' + query + '&displayCount='+displayCount);
    return this.http.get<Article[]>(this.baseUrl + '/api/articles?query=' + query + '&displayCount='+displayCount);
  }
}