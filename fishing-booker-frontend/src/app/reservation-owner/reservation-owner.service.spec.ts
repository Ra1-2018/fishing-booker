import { TestBed } from '@angular/core/testing';

import { ReservationOwnerService } from './reservation-owner.service';

describe('ReservationOwnerService', () => {
  let service: ReservationOwnerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReservationOwnerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
