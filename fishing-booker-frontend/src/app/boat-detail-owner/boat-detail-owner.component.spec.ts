import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatDetailOwnerComponent } from './boat-detail-owner.component';

describe('BoatDetailOwnerComponent', () => {
  let component: BoatDetailOwnerComponent;
  let fixture: ComponentFixture<BoatDetailOwnerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatDetailOwnerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatDetailOwnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
