import { TestBed } from '@angular/core/testing';

import { AdventureDetailOwnerService } from './adventure-detail-owner.service';

describe('AdventureDetailOwnerService', () => {
  let service: AdventureDetailOwnerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdventureDetailOwnerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
