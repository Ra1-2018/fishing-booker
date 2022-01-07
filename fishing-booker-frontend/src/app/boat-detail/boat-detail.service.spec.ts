import { TestBed } from '@angular/core/testing';

import { BoatDetailService } from './boat-detail.service';

describe('BoatDetailService', () => {
  let service: BoatDetailService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BoatDetailService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
