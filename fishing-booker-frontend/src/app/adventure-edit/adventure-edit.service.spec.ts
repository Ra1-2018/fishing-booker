import { TestBed } from '@angular/core/testing';

import { AdventureEditService } from './adventure-edit.service';

describe('AdventureEditService', () => {
  let service: AdventureEditService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdventureEditService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
