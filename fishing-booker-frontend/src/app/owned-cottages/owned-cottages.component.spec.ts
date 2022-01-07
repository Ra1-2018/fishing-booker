import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnedCottagesComponent } from './owned-cottages.component';

describe('OwnedCottagesComponent', () => {
  let component: OwnedCottagesComponent;
  let fixture: ComponentFixture<OwnedCottagesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OwnedCottagesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OwnedCottagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
