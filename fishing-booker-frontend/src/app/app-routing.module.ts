import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdventuresComponent } from './adventures/adventures.component';

const routes: Routes = [
  { path: 'adventures', component: AdventuresComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
