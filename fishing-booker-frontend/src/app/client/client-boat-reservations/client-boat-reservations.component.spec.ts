import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientBoatReservationsComponent } from './client-boat-reservations.component';

describe('ClientBoatReservationsComponent', () => {
  let component: ClientBoatReservationsComponent;
  let fixture: ComponentFixture<ClientBoatReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientBoatReservationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientBoatReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
