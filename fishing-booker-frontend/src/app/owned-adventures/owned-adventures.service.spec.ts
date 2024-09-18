import { TestBed } from '@angular/core/testing';

import { OwnedAdventuresService } from './owned-adventures.service';

describe('OwnedAdventuresService', () => {
  let service: OwnedAdventuresService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OwnedAdventuresService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
