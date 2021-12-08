import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdventureDetailComponent } from './adventure-detail/adventure-detail.component';
import { AdventuresComponent } from './adventures/adventures.component';
import { CottageDetailComponent } from './cottage-detail/cottage-detail.component';
import { CottagesComponent } from './cottages/cottages.component';

const routes: Routes = [
  { path: 'adventures', component: AdventuresComponent},
  { path: 'adventure/:id', component:AdventureDetailComponent},
  { path: 'cottages', component: CottagesComponent},
  { path: 'cottage/:id', component: CottageDetailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
