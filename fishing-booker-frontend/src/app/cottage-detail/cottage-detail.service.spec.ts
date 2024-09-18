import { TestBed } from '@angular/core/testing';

import { CottageDetailService } from './cottage-detail.service';

describe('CottageDetailService', () => {
  let service: CottageDetailService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CottageDetailService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
