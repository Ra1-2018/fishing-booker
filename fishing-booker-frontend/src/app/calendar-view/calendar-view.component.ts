import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { CalendarOptions, DateSelectArg, EventApi, EventClickArg } from '@fullcalendar/angular';
import { EventInput } from '@fullcalendar/angular';
import { CalendarViewService } from './calendar-view.service';

let eventGuid = 0;

export function createEventId() {
  return String(eventGuid++);
}

@Component({
  selector: 'app-calendar-view',
  templateUrl: './calendar-view.component.html',
  styleUrls: ['./calendar-view.component.css']
})
export class CalendarViewComponent implements OnInit{

  public readonly myFormGroup:FormGroup;

  constructor(private router: Router, 
    private route: ActivatedRoute,
    public readonly calendarService: CalendarViewService,
    public dialog: MatDialog,
    private readonly formBuilder: FormBuilder) {
      this.myFormGroup = this.formBuilder.group({
        id: 0,
        startDate: [null, Validators.required],
        endDate: [null, Validators.required],
        ownerId: Number(this.route.snapshot.paramMap.get('id'))
      });
    }

  id:any;
  actions: any[]=[];
  unavailablePeriods: any[]=[];
  reservations: any[]=[];
  INITIAL_EVENTS: EventInput[] =  [];
  calendarOptions!: CalendarOptions;
  currentEvents: EventApi[] = [];

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.getUnavailablePeriods();
    this.getReservations();
    this.getActions();
    setTimeout(() => {
      this.loadCalendar()
    }, 1000)
  }

  handleWeekendsToggle() {
    const { calendarOptions } = this;
    calendarOptions.weekends = !calendarOptions.weekends;
  }

  handleEvents(events: EventApi[]) {
    this.currentEvents = events;
  }

  getUnavailablePeriods() {
    this.calendarService.getUnavailablePeriodsOfOwner(this.id).subscribe((data) => {
      this.unavailablePeriods = data
      
      for (let u of this.unavailablePeriods) {
        var startTime = new Date(u.startDate);
        var end = new Date(u.endDate);
        this.INITIAL_EVENTS.push({
          id: createEventId(),
          title: 'Unavailable for all of my adventures',
          start: startTime.toISOString().replace(/T.*$/, ''), 
          end: end.toISOString().replace(/T.*$/, ''),
          color: '#c73030'
        })
      }
    });
  }

  addOwnerUnavailablePeriod(){
    if (this.myFormGroup.invalid) {
      alert('Invalid input');
      return;
    }

    this.calendarService.addOwnerUnavailablePeriod(this.myFormGroup.getRawValue()).subscribe({
      next: (data) => {
      alert("Succesfully created!")
      this.loadCalendar();
    },
      error: (err) => {alert("Error has occured, free period was not created!")}
    });

    setTimeout(() => {
      this.loadCalendar()
    }, 1000)
  }

  getReservations(){
    this.calendarService.getAllReservationsOfOwner().subscribe((data) => {
      this.reservations = data
      console.log(this.reservations[0])
      for (let r of this.reservations) {
        var startTime = new Date(r.reservationStartDateAndTime);
        var end = new Date();
        end.setDate(startTime.getDate()+r.durationInDays)
        this.INITIAL_EVENTS.push({
          id: createEventId(),
          title: 'Reservation of: ' + r.service.name + '\nMax people number: ' + r.numberOfPeople + '\nPrice: ' + r.price,
          start: startTime.toISOString().replace(/T.*$/, ''), 
          end: end.toISOString().replace(/T.*$/, ''),
          color: '#3d405b'
        })
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
        this.INITIAL_EVENTS.push({
          id: createEventId(),
          title: 'Action of: ' + a.service.name + '\nMax people number: ' + a.maxNumberOfPeople + '\nPrice: ' + a.price,
          start: startTime.toISOString().replace(/T.*$/, ''), 
          end: end.toISOString().replace(/T.*$/, ''),
          color: '#81b29a'
        })
      }
    });
  }

  loadCalendar() {
    this.calendarOptions = {
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,dayGridWeek'
      },
      initialView: 'dayGridMonth',
      initialEvents: this.INITIAL_EVENTS,
      weekends: true,
      editable: false,
      selectable: false,
      selectMirror: true,
      dayMaxEvents: true,
      eventsSet: this.handleEvents.bind(this)
    };
  }

}
