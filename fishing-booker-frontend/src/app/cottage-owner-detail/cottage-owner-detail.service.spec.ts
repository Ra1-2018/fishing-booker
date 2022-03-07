import { TestBed } from '@angular/core/testing';

import { CottageOwnerDetailService } from './cottage-owner-detail.service';

describe('CottageOwnerDetailService', () => {
  let service: CottageOwnerDetailService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CottageOwnerDetailService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
