import { TestBed } from '@angular/core/testing';

import { ClientBoatReservationsService } from './client-boat-reservations.service';

describe('ClientBoatReservationsService', () => {
  let service: ClientBoatReservationsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientBoatReservationsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
