import { TestBed } from '@angular/core/testing';

import { BoatDetailOwnerService } from './boat-detail-owner.service';

describe('BoatDetailOwnerService', () => {
  let service: BoatDetailOwnerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BoatDetailOwnerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
