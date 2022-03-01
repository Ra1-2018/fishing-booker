import { TestBed } from '@angular/core/testing';

import { ClientOrdinaryReservationService } from './client-ordinary-reservation.service';

describe('ClientOrdinaryReservationService', () => {
  let service: ClientOrdinaryReservationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientOrdinaryReservationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
