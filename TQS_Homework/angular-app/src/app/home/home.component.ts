import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { ApiServiceService } from '../api-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  form1Ready: boolean;
  form1Group: FormGroup;
  form2Ready: boolean;
  form2Group: FormGroup;
  erro1: boolean;
  erro2: boolean;

  constructor(private router: Router, private apiService: ApiServiceService) {
    this.form1Ready = false;
    this.form2Ready = false;
    this.erro1 = false;
    this.erro2 = false;
   }

  ngOnInit(): void {
    localStorage.removeItem('city');
    localStorage.removeItem('lat');
    localStorage.removeItem('lon');
    localStorage.removeItem('statistics');
    this.form1Ready = false;
    this.form2Ready = false;
    this.erro1 = false;
    this.erro2 = false;
  }


  searchByCity() {
    this.erro1 = false;
    var city = document.getElementById("inputCity")['value'];
    if (city !== "") {
      localStorage.setItem('city', city);
      this.router.navigate(['/result']);
    } else {
      this.erro1 = true;
    }
  }

  searchByLatLon() {
    this.erro2 = false;
    var lat = document.getElementById("inputLat")['value'];
    var lon = document.getElementById("inputLon")['value'];
    if (isNaN(parseFloat(lat)) || isNaN(parseFloat(lon))) 
    {
      this.erro2 = true;
      return
    }

    if (lat !== "" && lon !== "") {
      localStorage.setItem('lat', lat);
      localStorage.setItem('lon', lon);
      this.router.navigate(['/result']);
    } else {
      this.erro2 = true;
    }
  }

  searchStatistics() {
    localStorage.setItem('statistics', "true");
    this.router.navigate(['/result']);
  }
}
