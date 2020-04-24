import { Component, OnInit } from '@angular/core';
import { Sensors } from '../model/sensor';
import { SensorService } from 'src/app/sensor.service';
import { interval } from 'rxjs';

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {
  sensors: Sensors[];
  constructor(private SensorService: SensorService) { }

  ngOnInit() {
    this.getSensorDetails();
    setTimeout(() => {
      this.getSensorDetails();
    }, 5000);
  }

  getSensorDetails() {
    this.SensorService.getSensorDetails()
    .subscribe((data:any) => {
      console.log(data)
      this.sensors = data.data;
    })
  }

}
