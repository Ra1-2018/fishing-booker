import { TestBed } from '@angular/core/testing';

import { AdventureDetailService } from './adventure-detail.service';

describe('AdventureDetailService', () => {
  let service: AdventureDetailService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdventureDetailService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
