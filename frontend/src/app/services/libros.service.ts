import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Libro } from '../models/libro';
import { catchError, tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

const httpOptionsUsingUrlEncoded = {
  headers: new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' })
};

@Injectable({
  providedIn: 'root'
})
export class LibrosService {
  endpoint: string = "http://localhost:8080/libro";

  constructor(private httpClient: HttpClient) { }

  getLibros(): Observable<Libro[]>{
    return this.httpClient.get<Libro[]>(this.endpoint);
  }

  getLibroById(id: number): Observable<Libro>{
    return this.httpClient.get<Libro>(this.endpoint + "/" + id);
  }

  addLibro(libro: Libro): Observable<Libro>{
    let bodyEncoded = new URLSearchParams();
    bodyEncoded.append("title", libro.title);
    bodyEncoded.append("author", libro.author);
    bodyEncoded.append("year", libro.year.toString());
    const body = bodyEncoded.toString();

    console.log("addLibro")
    console.log(JSON.stringify(libro))
   return this.httpClient.post<Libro>(this.endpoint, body, httpOptionsUsingUrlEncoded);
  }

  deleteLibro(idLibro: number): Observable<Libro>{
    return this.httpClient.delete<Libro>(this.endpoint + "/" + idLibro);
  }

}
