import { TestBed } from '@angular/core/testing';

import { InstructorDetailService } from './instructor-detail.service';

describe('InstructorDetailService', () => {
  let service: InstructorDetailService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InstructorDetailService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
