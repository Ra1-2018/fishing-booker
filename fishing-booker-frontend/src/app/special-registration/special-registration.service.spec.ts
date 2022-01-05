import { TestBed } from '@angular/core/testing';

import { SpecialRegistrationService } from './special-registration.service';

describe('SpecialRegistrationService', () => {
  let service: SpecialRegistrationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SpecialRegistrationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
