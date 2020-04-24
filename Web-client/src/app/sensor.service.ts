import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http'
import * as Rx from 'rxjs/Rx'
import { from, Observable, throwError} from 'rxjs'
import { map, catchError } from 'rxjs/Operators'

import { Sensors } from './components/model/sensor';

@Injectable({
  providedIn: 'root'
})
export class SensorService {

  constructor(private http: HttpClient) { }

  // fetch data
  getSensorDetails() {
    return this.http.get('http://localhost:8080/location').pipe(
      map((data: Sensors[]) => {
        return data
      }), catchError(error => {
        return throwError('Something went wrong!');
      })
    )
  }
}
