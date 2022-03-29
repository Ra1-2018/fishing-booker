import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationOwnerComponent } from './reservation-owner.component';

describe('ReservationOwnerComponent', () => {
  let component: ReservationOwnerComponent;
  let fixture: ComponentFixture<ReservationOwnerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReservationOwnerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReservationOwnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
