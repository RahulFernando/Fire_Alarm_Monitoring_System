import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { from, Observable, throwError} from 'rxjs'
import { catchError } from 'rxjs/Operators'

import { Sensors } from './components/model/sensor';

@Injectable({
  providedIn: 'root'
})
export class SensorService {

  constructor(private http: HttpClient) { }

  // fetch data
  getSensorDetails(): Observable<Sensors[]> {
    return this.http.get<Sensors[]>(`http://localhost:8080/location`).pipe(
      catchError(error => {
        return throwError('Something went wrong!');
      })
    )
  }
}
