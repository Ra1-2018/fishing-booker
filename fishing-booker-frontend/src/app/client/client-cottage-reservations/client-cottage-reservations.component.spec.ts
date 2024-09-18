import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientCottageReservationsComponent } from './client-cottage-reservations.component';

describe('ClientCottageReservationsComponent', () => {
  let component: ClientCottageReservationsComponent;
  let fixture: ComponentFixture<ClientCottageReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientCottageReservationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientCottageReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
