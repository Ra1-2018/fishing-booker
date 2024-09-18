import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CottageOwnersComponent } from './cottage-owners.component';

describe('CottageOwnersComponent', () => {
  let component: CottageOwnersComponent;
  let fixture: ComponentFixture<CottageOwnersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CottageOwnersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CottageOwnersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
