import { TestBed } from '@angular/core/testing';

import { ClientSubscriptionsService } from './client-subscriptions.service';

describe('ClientSubscriptionsService', () => {
  let service: ClientSubscriptionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientSubscriptionsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
