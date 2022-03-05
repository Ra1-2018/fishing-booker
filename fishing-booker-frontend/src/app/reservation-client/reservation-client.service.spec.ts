import { TestBed } from '@angular/core/testing';

import { ReservationClientService } from './reservation-client.service';

describe('ReservationClientService', () => {
  let service: ReservationClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReservationClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
