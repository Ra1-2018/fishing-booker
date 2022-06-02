import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

export interface ActionDialogData {
  startTime: any;
  durationInDays: any;
  maxNumberOfPeople: any;
  price: any;
  additionalServices: any;
}

@Component({
  selector: 'app-action-dialog',
  templateUrl: './action-dialog.component.html',
  styleUrls: ['./action-dialog.component.css']
})
export class ActionDialogComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public data: ActionDialogData) { }

}
