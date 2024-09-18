import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatOwnersComponent } from './boat-owners.component';

describe('BoatOwnersComponent', () => {
  let component: BoatOwnersComponent;
  let fixture: ComponentFixture<BoatOwnersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatOwnersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatOwnersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
