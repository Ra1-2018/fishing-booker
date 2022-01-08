import { TestBed } from '@angular/core/testing';

import { ClientCottageReservationsService } from './client-cottage-reservations.service';

describe('ClientCottageReservationsService', () => {
  let service: ClientCottageReservationsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientCottageReservationsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
