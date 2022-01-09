import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnedBoatsComponent } from './owned-boats.component';

describe('OwnedBoatsComponent', () => {
  let component: OwnedBoatsComponent;
  let fixture: ComponentFixture<OwnedBoatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OwnedBoatsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OwnedBoatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
