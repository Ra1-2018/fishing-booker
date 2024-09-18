import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientUpcomingReservationsComponent } from './client-upcoming-reservations.component';

describe('ClientUpcomingReservationsComponent', () => {
  let component: ClientUpcomingReservationsComponent;
  let fixture: ComponentFixture<ClientUpcomingReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientUpcomingReservationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientUpcomingReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
