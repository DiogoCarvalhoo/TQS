import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/internal/Observable';
import {HttpClient, HttpHeaders} from '@angular/common/http';


const httpOptions = {
  headers: new HttpHeaders( {'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ApiServiceService {

  private baseUrl = 'http://localhost:8080/api/';


  constructor(private http: HttpClient) { }

  searchByCity(cityPesquisada: string): Observable<Object> {
    const url = this.baseUrl + 'air?city=' + cityPesquisada;
    return this.http.get<Object>(url);
  }

  searchByLatLon(latPesquisada: string, lonPesquisada): Observable<Object> {
    const url = this.baseUrl + 'air?lat=' + latPesquisada + '&lon=' + lonPesquisada;
    return this.http.get<Object>(url);
  }

  searchStatistics(): Observable<Object> {
    const url = this.baseUrl + 'statistics';
    return this.http.get<Object>(url);
  }
}
