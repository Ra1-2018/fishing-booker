import { TestBed } from '@angular/core/testing';

import { CottageDetailOwnerService } from './cottage-detail-owner.service';

describe('CottageDetailOwnerService', () => {
  let service: CottageDetailOwnerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CottageDetailOwnerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
