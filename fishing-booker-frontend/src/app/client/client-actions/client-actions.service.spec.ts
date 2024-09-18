import { TestBed } from '@angular/core/testing';

import { ClientActionsService } from './client-actions.service';

describe('ClientActionsService', () => {
  let service: ClientActionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientActionsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
