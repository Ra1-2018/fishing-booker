import { TestBed } from '@angular/core/testing';

import { BoatOwnerDetailService } from './boat-owner-detail.service';

describe('BoatOwnerDetailService', () => {
  let service: BoatOwnerDetailService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BoatOwnerDetailService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
