import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CalendarOptions, DateSelectArg, EventApi, EventClickArg } from '@fullcalendar/angular';
import { EventInput } from '@fullcalendar/angular';
//import { start } from 'repl';
import { Observable } from 'rxjs';
import { CalendarViewService } from './calendar-view.service';

let eventGuid = 0;
const TODAY_STR = new Date().toISOString().replace(/T.*$/, ''); // YYYY-MM-DD of today

export function createEventId() {
  return String(eventGuid++);
}

@Component({
  selector: 'app-calendar-view',
  templateUrl: './calendar-view.component.html',
  styleUrls: ['./calendar-view.component.css']
})
export class CalendarViewComponent implements OnInit{

  constructor(private router: Router, 
    private route: ActivatedRoute,
    public readonly calendarService: CalendarViewService) {}

  id:any;
  actions: any[]=[];
  unavailablePeriods: any[]=[];
  reservations: any[]=[];
  INITIAL_EVENTS: EventInput[] =  [];
  calendarOptions!: CalendarOptions;

  // calendarOptions: CalendarOptions = {
  //   headerToolbar: {
  //     left: 'prev,next today',
  //     center: 'title',
  //     right: 'dayGridMonth,dayGridWeek'
  //   },
  //   initialView: 'dayGridMonth',
  //   initialEvents: this.INITIAL_EVENTS, // alternatively, use the `events` setting to fetch from a feed
  //   weekends: true,
  //   editable: true,
  //   selectable: true,
  //   selectMirror: true,
  //   dayMaxEvents: true,
  //   // select: this.handleDateSelect.bind(this),
  //   // eventClick: this.handleEventClick.bind(this),
  //   eventsSet: this.handleEvents.bind(this)
  // };
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

  handleDateSelect(selectInfo: DateSelectArg) {
    const title = prompt('Please enter a new title for your event');
    const calendarApi = selectInfo.view.calendar;

    calendarApi.unselect(); // clear date selection

    if (title) {
      calendarApi.addEvent({
        id: createEventId(),
        title,
        start: selectInfo.startStr,
        end: selectInfo.endStr,
        allDay: selectInfo.allDay
      });
    }
  }

  handleEventClick(clickInfo: EventClickArg) {
    if (confirm(`Are you sure you want to delete the event '${clickInfo.event.title}'`)) {
      clickInfo.event.remove();
    }
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
          title: 'Unavailable',
          start: startTime.toISOString().replace(/T.*$/, ''), 
          end: end.toISOString().replace(/T.*$/, ''),
          color: '#595656'
        })
      }
    });
  }

  // addUnavailablePeriod(){
  //   if (this.myFormGroup.invalid) {
  //     alert('Invalid input');
  //     return;
  //   }

  //   this.calendarService.addUnavailablePeriod(this.myFormGroup.getRawValue()).subscribe({
  //     next: (data) => {
  //     alert("Succesfully created!")
  //     this.loadCalendar();
  //   },
  //     error: (err) => {alert("Error has occured, free period was not created!")}
  //   });
  // }

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
          title: 'Reservation of: ' + r.service.name,
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
          title: 'Action of: ' + a.service.name,
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
      editable: true,
      selectable: true,
      selectMirror: true,
      dayMaxEvents: true,
      select: this.handleDateSelect.bind(this),
      eventClick: this.handleEventClick.bind(this),
      eventsSet: this.handleEvents.bind(this)
    };
  }

}
