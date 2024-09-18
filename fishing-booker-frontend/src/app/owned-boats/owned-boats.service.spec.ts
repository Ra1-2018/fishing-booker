import { TestBed } from '@angular/core/testing';

import { OwnedBoatsService } from './owned-boats.service';

describe('OwnedBoatsService', () => {
  let service: OwnedBoatsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OwnedBoatsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
