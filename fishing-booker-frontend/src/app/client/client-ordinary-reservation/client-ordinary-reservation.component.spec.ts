import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientOrdinaryReservationComponent } from './client-ordinary-reservation.component';

describe('ClientOrdinaryReservationComponent', () => {
  let component: ClientOrdinaryReservationComponent;
  let fixture: ComponentFixture<ClientOrdinaryReservationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientOrdinaryReservationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientOrdinaryReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
