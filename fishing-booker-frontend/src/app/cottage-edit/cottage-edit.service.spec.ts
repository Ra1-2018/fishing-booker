import { TestBed } from '@angular/core/testing';

import { CottageEditService } from './cottage-edit.service';

describe('CottageEditService', () => {
  let service: CottageEditService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CottageEditService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
