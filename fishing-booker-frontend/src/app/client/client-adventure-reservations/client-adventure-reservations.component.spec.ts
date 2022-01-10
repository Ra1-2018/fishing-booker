import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientAdventureReservationsComponent } from './client-adventure-reservations.component';

describe('ClientAdventureReservationsComponent', () => {
  let component: ClientAdventureReservationsComponent;
  let fixture: ComponentFixture<ClientAdventureReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientAdventureReservationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientAdventureReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
