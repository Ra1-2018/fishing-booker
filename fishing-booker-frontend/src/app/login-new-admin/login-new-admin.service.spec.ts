import { TestBed } from '@angular/core/testing';

import { LoginNewAdminService } from './login-new-admin.service';

describe('LoginNewAdminService', () => {
  let service: LoginNewAdminService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoginNewAdminService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
