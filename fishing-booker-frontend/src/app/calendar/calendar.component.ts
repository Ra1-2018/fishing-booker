import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { CalendarOptions, EventApi, EventClickArg, EventInput } from '@fullcalendar/angular';
import { ActionDialogComponent } from '../action-dialog/action-dialog.component';
import { LoginService } from '../login/login.service';
import { ReservationDialogComponent } from '../reservation-dialog/reservation-dialog.component';
import { CalendarService } from './calendar.service';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {

  actions: any[]=[];
  unavailablePeriods: any[]=[];
  reservations: any[]=[];
  displayEvents = [] as EventInput[]
  calendarOptions!: CalendarOptions;
  currentEvents: EventApi[] = [];
  id:any;
  
  public readonly myFormGroup: FormGroup;

  constructor(public readonly loginService: LoginService,
              public dialog: MatDialog,
              private router: Router, 
              private route: ActivatedRoute,
              public readonly calendarService: CalendarService,
              private readonly formBuilder: FormBuilder) {
                this.myFormGroup = this.formBuilder.group({
                  id: 0,
                  startDate: [null, Validators.required],
                  endDate: [null, Validators.required],
                });
               }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.getUnavailablePeriods();
    this.getReservations();
    this.getActions();
    this.loadCalendar();
  }

  getUnavailablePeriods() {
    this.calendarService.getUnavailablePeriods(this.id).subscribe((data) => {
      this.unavailablePeriods = data
      
      for (let u of this.unavailablePeriods) {
        var startTime = new Date(u.startDate);
        var end = new Date(u.endDate);
        this.displayEvents.push({
          title: 'Unavailable',
          start: startTime.toISOString(), 
          end: end.toISOString(),
          color: '#595656'
        })
        this.loadCalendar();
      }
    });
  }

  addUnavailablePeriod(){
    if (this.myFormGroup.invalid) {
      alert('Invalid input');
      return;
    }

    this.calendarService.addUnavailablePeriod(this.myFormGroup.getRawValue()).subscribe({
      next: (data) => {
      alert("Succesfully created!")
      this.loadCalendar();
    },
      error: (err) => {alert("Error has occured, free period was not created!")}
    });
  }

  getReservations(){
    this.calendarService.getAllReservationsOfOwner().subscribe((data) => {
      this.reservations = data
      
      for (let r of this.reservations) {
        var startTime = new Date(r.reservationStartDateAndTime);
        var end = new Date();
        end.setDate(startTime.getDate()+r.durationInDays)
        this.displayEvents.push({
          title: 'Reservation',
          start: startTime.toISOString(), 
          end: end.toISOString(),
          color: '#3d405b'
        })
        this.loadCalendar();
      }
    });
  }

  getActions() {
    this.calendarService.getAllActionsOfOwner().subscribe((data) => {
      this.actions = data
      
      for (let a of this.actions) {
        var startTime = new Date(a.startTime);
        var end = new Date();
        end.setDate(startTime.getDate()+a.durationInDays)
        this.displayEvents.push({
          title: 'Action',
          start: startTime.toISOString(), 
          end: end.toISOString(),
          color: '#81b29a'
        })
        this.loadCalendar();
      }
    });
  }

  handleDateClick(clickInfo: EventClickArg) {
    if (clickInfo.event.title !== "Unavailable period") {

      for (let a of this.actions) {
        if (new Date(a.startTime).getTime() === clickInfo.event.start?.getTime()
          && new Date(a.startTime.getDate()+a.durationInDays).getTime() === clickInfo.event.end?.getTime()) {
            this.dialog.open(ActionDialogComponent, {
              maxWidth: '800px',
              data: {
                startTime: a.startTime,
                durationInDays: a.durationInDays,
                maxNumberOfPeople: a.maxNumberOfPeople,
                price: a.price,
                additionalServices: a.additionalServices
              }
            });
          break;
        }
      }

      for (let r of this.reservations) {
        if (new Date(r.reservationStartDateAndTime).getTime() === clickInfo.event.start?.getTime()
          && new Date(r.startTime.getDate()+r.durationInDays).getTime() === clickInfo.event.end?.getTime()) {
            this.dialog.open(ReservationDialogComponent, {
              maxWidth: '800px',
              data: {
                reservationStartDateAndTime: r.reservationStartDateAndTime,
                durationInDays: r.durationInDays,
                numberOfPeople: r.numberOfPeople,
                price: r.price,
                additionalServices: r.additionalServices
              }
            });
          break;
        }
      }
    }
  }

  loadCalendar() {
    this.calendarOptions = {
      initialView: 'dayGridMonth',
      initialEvents: this.displayEvents,
      weekends: true,
      displayEventTime: true,
      editable: false,
      selectable: false,
      firstDay: 1,
      eventClick: this.handleDateClick.bind(this),
      eventsSet: this.handleEvents.bind(this)
    };
  }

  handleEvents(events: EventApi[]) {
    this.currentEvents = events;
  }
}
