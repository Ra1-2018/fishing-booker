import { TestBed } from '@angular/core/testing';

import { ClientAdventuresReservationsService } from './client-adventures-reservations.service';

describe('ClientAdventuresReservationsService', () => {
  let service: ClientAdventuresReservationsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientAdventuresReservationsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
