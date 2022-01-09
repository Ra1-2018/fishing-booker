import { TestBed } from '@angular/core/testing';

import { BoatEditService } from './boat-edit.service';

describe('BoatEditService', () => {
  let service: BoatEditService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BoatEditService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
