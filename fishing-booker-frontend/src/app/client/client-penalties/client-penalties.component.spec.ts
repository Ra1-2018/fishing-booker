import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientPenaltiesComponent } from './client-penalties.component';

describe('ClientPenaltiesComponent', () => {
  let component: ClientPenaltiesComponent;
  let fixture: ComponentFixture<ClientPenaltiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientPenaltiesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientPenaltiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
