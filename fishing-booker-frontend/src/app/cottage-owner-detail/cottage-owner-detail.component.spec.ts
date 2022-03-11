import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CottageOwnerDetailComponent } from './cottage-owner-detail.component';

describe('CottageOwnerDetailComponent', () => {
  let component: CottageOwnerDetailComponent;
  let fixture: ComponentFixture<CottageOwnerDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CottageOwnerDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CottageOwnerDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
