import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiServiceService } from '../api-service.service';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit {
  city: string;
  lat: string;
  lon: string;
  statistics: boolean;
  misses: number;
  hits: number;
  requests: number;
  dew: string = "Sem Valor";
  h: string = "Sem Valor";
  no2: string = "Sem Valor";
  o3: string= "Sem Valor";
  p: string= "Sem Valor";
  pm10: string= "Sem Valor";
  pm25: string= "Sem Valor";
  so2: string= "Sem Valor";
  t: string= "Sem Valor";
  w: string= "Sem Valor";
  wg: string= "Sem Valor";
  readyToDisplay: boolean;
  erro: boolean;

  constructor(private router: Router, private apiService: ApiServiceService) {
    this.readyToDisplay = false;
    this.erro = false;
  }

  ngOnInit(): void {
    this.readyToDisplay = false;
    this.erro = false;
    this.city = localStorage.getItem('city');
    this.lat = localStorage.getItem('lat');
    this.lon = localStorage.getItem('lon');
    this.statistics = (localStorage.getItem('statistics') === 'true')
    if (this.city !== null) {
      this.searchByCity()
    } else if (this.statistics) {
      this.searchStatistics()
    } else {
      this.searchByLatLon()
    }
  }


  searchByCity() {
    this.erro = false;
    this.apiService.searchByCity(this.city).subscribe(airData => {
      console.log(airData)
      this.city = airData["data"]["city"]["name"];
      this.lat = airData["data"]["city"]["geo"][0];
      this.lon = airData["data"]["city"]["geo"][1];
      if (airData["data"]["iaqi"]["dew"] !== null)
        this.dew = airData["data"]["iaqi"]["dew"]["v"];
      if (airData["data"]["iaqi"]["h"] !== null)
        this.h = airData["data"]["iaqi"]["h"]["v"];
      if (airData["data"]["iaqi"]["no2"] !== null)
        this.no2 = airData["data"]["iaqi"]["no2"]["v"];
      if (airData["data"]["iaqi"]["o3"] !== null)
        this.o3 = airData["data"]["iaqi"]["o3"]["v"];
      if (airData["data"]["iaqi"]["p"] !== null)
        this.p = airData["data"]["iaqi"]["p"]["v"];
      if (airData["data"]["iaqi"]["pm10"] !== null)
        this.pm10 = airData["data"]["iaqi"]["pm10"]["v"];
      if (airData["data"]["iaqi"]["pm25"] !== null)
        this.pm25 = airData["data"]["iaqi"]["pm25"]["v"];
      if (airData["data"]["iaqi"]["so2"] !== null)  
        this.so2 = airData["data"]["iaqi"]["so2"]["v"];
      if (airData["data"]["iaqi"]["t"] !== null)  
        this.t = airData["data"]["iaqi"]["t"]["v"];
      if (airData["data"]["iaqi"]["w"] !== null)  
        this.w = airData["data"]["iaqi"]["w"]["v"];
      if (airData["data"]["iaqi"]["wg"] !== null)
        this.wg = airData["data"]["iaqi"]["wg"]["v"];
      this.readyToDisplay = true;
    }, error => {
      this.erro = true;
      console.log(error)
    })

  }

  searchByLatLon() {
    this.erro = false;
    this.apiService.searchByLatLon(this.lat, this.lon).subscribe(airData => {
      console.log(airData)
      this.city = airData["data"]["city"]["name"];
      this.lat = airData["data"]["city"]["geo"][0];
      this.lon = airData["data"]["city"]["geo"][1];
      if (airData["data"]["iaqi"]["dew"] !== null)
        this.dew = airData["data"]["iaqi"]["dew"]["v"];
      if (airData["data"]["iaqi"]["h"] !== null)
        this.h = airData["data"]["iaqi"]["h"]["v"];
      if (airData["data"]["iaqi"]["no2"] !== null)
        this.no2 = airData["data"]["iaqi"]["no2"]["v"];
      if (airData["data"]["iaqi"]["o3"] !== null)
        this.o3 = airData["data"]["iaqi"]["o3"]["v"];
      if (airData["data"]["iaqi"]["p"] !== null)
        this.p = airData["data"]["iaqi"]["p"]["v"];
      if (airData["data"]["iaqi"]["pm10"] !== null)
        this.pm10 = airData["data"]["iaqi"]["pm10"]["v"];
      if (airData["data"]["iaqi"]["pm25"] !== null)
        this.pm25 = airData["data"]["iaqi"]["pm25"]["v"];
      if (airData["data"]["iaqi"]["so2"] !== null)  
        this.so2 = airData["data"]["iaqi"]["so2"]["v"];
      if (airData["data"]["iaqi"]["t"] !== null)  
        this.t = airData["data"]["iaqi"]["t"]["v"];
      if (airData["data"]["iaqi"]["w"] !== null)  
        this.w = airData["data"]["iaqi"]["w"]["v"];
      if (airData["data"]["iaqi"]["wg"] !== null)
        this.wg = airData["data"]["iaqi"]["wg"]["v"];
      this.readyToDisplay = true;
    }, error => {
      this.erro = true;
      console.log(error)
    })
  }

  searchStatistics() {
    this.erro = false;
    this.apiService.searchStatistics().subscribe(dados => {
      this.misses = dados['numberOfMisses']
      this.hits = dados['numberOfHits']
      this.requests = dados['numberOfRequests']
      this.readyToDisplay = true;
    }, error => {
      this.erro = true;
      console.log(error)
    })
  }

  returnHome() {
    this.router.navigate(['/home']);
  }
}
