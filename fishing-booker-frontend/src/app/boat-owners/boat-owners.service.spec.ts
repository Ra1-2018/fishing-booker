import { TestBed } from '@angular/core/testing';

import { BoatOwnersService } from './boat-owners.service';

describe('BoatOwnersService', () => {
  let service: BoatOwnersService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BoatOwnersService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
