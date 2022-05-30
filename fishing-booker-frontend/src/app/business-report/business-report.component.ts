import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ChartData, ChartOptions } from 'chart.js';
import { BusinessReportService } from './business-report.service';

@Component({
  selector: 'app-business-report',
  templateUrl: './business-report.component.html',
  styleUrls: ['./business-report.component.css']
})
export class BusinessReportComponent implements OnInit {

  errorMessage = '';
  weeklyEarnings = 0;
  monthlyEarnings = 0;
  yearlyEarnings = 0;
  services: any[] = []

  constructor(private businessReportService: BusinessReportService, private cdr: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.getBusinessReport();
  }

  getBusinessReport(): void {
    this.businessReportService.getBusinessReport(parseInt(localStorage.getItem('userId') as string)).subscribe({
      next: report => { 
      this.weeklyBusinessReport.datasets = [{ label: 'Weekly reservations', data: report.weeklyReservations , tension: 0 }];
      this.monthlyBusinessReport.datasets = [{ label: 'Monthly reservations', data: report.monthlyReservations , tension: 0 }];
      this.yearlyBusinessReport.datasets = [{ label: 'Yearly reservations', data: report.yearlyReservations , tension: 0 }];
      this.weeklyEarnings = report.weeklyEarnings;
      this.monthlyEarnings = report.monthlyEarnings;
      this.yearlyEarnings = report.yearlyEarnings;
      this.services = report.services;
      this.cdr.detectChanges();
    },
      
      error: err => this.errorMessage = err
    })
  }

  weeklyBusinessReport: ChartData<'line'> = {
    labels: ['', '', '', '', '', '', ''],
    datasets: [ ],
  };

  monthlyBusinessReport: ChartData<'line'> = {
    labels: [ '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''],
    datasets: [ ],
  };

  yearlyBusinessReport: ChartData<'line'> = {
    labels: new Array<string>(365).fill(''),
    datasets: [ ]
  };

  weeklyOptions: ChartOptions = {
    responsive: true,
    plugins: {
      title: {
        display: true,
        text: 'Report',
      },
    },
  };

  monthlyOptions: ChartOptions = {
    responsive: true,
    plugins: {
      title: {
        display: true,
        text: 'Report',
      },
    },
  };

  yearlyOptions: ChartOptions = {
    responsive: true,
    plugins: {
      title: {
        display: true,
        text: 'Report',
      },
    },
  };
}
