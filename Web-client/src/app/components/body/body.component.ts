import { Component, OnInit } from '@angular/core';
import { Sensors } from '../model/sensor';
import { SensorService } from 'src/app/sensor.service';

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {
  sensors: Sensors[];
  constructor(private SensorService: SensorService) { 
    setInterval(() => this.getSensorDetails(), 40000);
  }

  ngOnInit() {
    this.getSensorDetails();
  }

  getSensorDetails() {
    this.SensorService.getSensorDetails()
    .subscribe((data:any) => {
      console.log(data)
      this.sensors = data;
    })
  }

  onEverySecond() {
    console.log('second');
  }

}
