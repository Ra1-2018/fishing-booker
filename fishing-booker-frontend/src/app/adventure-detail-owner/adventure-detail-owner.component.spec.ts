import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventureDetailOwnerComponent } from './adventure-detail-owner.component';

describe('AdventureDetailOwnerComponent', () => {
  let component: AdventureDetailOwnerComponent;
  let fixture: ComponentFixture<AdventureDetailOwnerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdventureDetailOwnerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventureDetailOwnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
