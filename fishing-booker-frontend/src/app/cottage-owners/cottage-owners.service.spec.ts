import { TestBed } from '@angular/core/testing';

import { CottageOwnersService } from './cottage-owners.service';

describe('CottageOwnersService', () => {
  let service: CottageOwnersService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CottageOwnersService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
