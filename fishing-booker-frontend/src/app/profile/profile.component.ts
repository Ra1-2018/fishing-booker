import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProfileService } from './profile.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  public readonly myFormGroup: FormGroup;

  constructor(private profileService: ProfileService,
              private readonly formBuilder: FormBuilder) {
                this.myFormGroup = this.formBuilder.group({
                  email: ['', Validators.compose([Validators.required, Validators.email])],
                  password: ['', Validators.required],
                  name: [],
                  surname: [],
                  address: [],
                  city: [],
                  country: [],
                  telephone: []
              });
               }

  ngOnInit(): void {
    this.retrieveData();
  }

  private retrieveData(): void {
    this.profileService.getUser()
        .subscribe((res: any) => {
            // Assuming res has a structure like:
            // res = {
            //     field1: "some-string",
            //     field2: "other-string",
            //     subgroupName: {
            //         subfield2: "another-string"
            //     },
            // }
            // Values in res that don't line up to the form structure
            // are discarded. You can also pass in your own object you
            // construct ad-hoc.
            this.myFormGroup.patchValue(res);
        });
}

  public onClickSubmit(): void {

  }
}
