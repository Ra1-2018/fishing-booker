import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { AdventuresComponent } from './adventures/adventures.component';
import { HttpClientModule} from '@angular/common/http';
import { AdventureDetailComponent } from './adventure-detail/adventure-detail.component';
import { CottagesComponent } from './cottages/cottages.component';
import { CottageDetailComponent } from './cottage-detail/cottage-detail.component';
import { RegistrationComponent } from './registration/registration.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    AdventuresComponent,
    AdventureDetailComponent,
    CottagesComponent,
    CottageDetailComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
