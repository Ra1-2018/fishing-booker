import { TestBed } from '@angular/core/testing';

import { ClientUpcomingReservationsService } from './client-upcoming-reservations.service';

describe('ClientUpcomingReservationsService', () => {
  let service: ClientUpcomingReservationsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientUpcomingReservationsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
