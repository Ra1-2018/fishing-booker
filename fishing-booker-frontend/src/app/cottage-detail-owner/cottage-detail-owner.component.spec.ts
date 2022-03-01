import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CottageDetailOwnerComponent } from './cottage-detail-owner.component';

describe('CottageDetailOwnerComponent', () => {
  let component: CottageDetailOwnerComponent;
  let fixture: ComponentFixture<CottageDetailOwnerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CottageDetailOwnerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CottageDetailOwnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
