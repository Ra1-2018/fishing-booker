import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { AdventuresComponent } from './adventures/adventures.component';
import { HttpClientModule} from '@angular/common/http';
import { AdventureDetailComponent } from './adventure-detail/adventure-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    AdventuresComponent,
    AdventureDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
