import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnedAdventuresComponent } from './owned-adventures.component';

describe('OwnedAdventuresComponent', () => {
  let component: OwnedAdventuresComponent;
  let fixture: ComponentFixture<OwnedAdventuresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OwnedAdventuresComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OwnedAdventuresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
