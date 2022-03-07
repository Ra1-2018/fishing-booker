import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatOwnerDetailComponent } from './boat-owner-detail.component';

describe('BoatOwnerDetailComponent', () => {
  let component: BoatOwnerDetailComponent;
  let fixture: ComponentFixture<BoatOwnerDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatOwnerDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatOwnerDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
