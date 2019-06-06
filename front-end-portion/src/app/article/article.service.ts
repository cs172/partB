// Nhat Le - UCR

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

  getArticles(query: string):  Observable<Article[]> {
    console.log(this.baseUrl + '/api/articles?query=' + query);
    return this.http.get<Article[]>(this.baseUrl + '/api/articles?query=' + query);
  }
}