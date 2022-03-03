import { TestBed } from '@angular/core/testing';

import { ClientComplaintService } from './client-complaint.service';

describe('ClientComplaintService', () => {
  let service: ClientComplaintService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientComplaintService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
