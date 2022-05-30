import { TestBed } from '@angular/core/testing';

import { BusinessReportService } from './business-report.service';

describe('BusinessReportService', () => {
  let service: BusinessReportService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BusinessReportService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
