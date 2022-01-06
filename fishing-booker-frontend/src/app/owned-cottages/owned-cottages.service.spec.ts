import { TestBed } from '@angular/core/testing';

import { OwnedCottagesService } from './owned-cottages.service';

describe('OwnedCottagesService', () => {
  let service: OwnedCottagesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OwnedCottagesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
