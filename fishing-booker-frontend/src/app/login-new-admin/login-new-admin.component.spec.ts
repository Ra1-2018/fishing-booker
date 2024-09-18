import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginNewAdminComponent } from './login-new-admin.component';

describe('LoginNewAdminComponent', () => {
  let component: LoginNewAdminComponent;
  let fixture: ComponentFixture<LoginNewAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginNewAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginNewAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
