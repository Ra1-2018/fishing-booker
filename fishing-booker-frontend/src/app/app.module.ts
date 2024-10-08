import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { AdventuresComponent } from './adventures/adventures.component';
import { HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { AdventureDetailComponent } from './adventure-detail/adventure-detail.component';
import { CottagesComponent } from './cottages/cottages.component';
import { CottageDetailComponent } from './cottage-detail/cottage-detail.component';
import { RegistrationComponent } from './registration/registration.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
//import { FlatpickrModule } from 'angularx-flatpickr';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { SpecialRegistrationComponent } from './special-registration/special-registration.component';
import { BoatsComponent } from './boats/boats.component';
import { BoatDetailComponent } from './boat-detail/boat-detail.component';
import { OwnedCottagesComponent } from './owned-cottages/owned-cottages.component';
import { CottageEditComponent } from './cottage-edit/cottage-edit.component';
import { TokenInterceptor } from './interceptor/tokenInterceptor';
import { ClientCottageReservationsComponent } from './client/client-cottage-reservations/client-cottage-reservations.component';
import { ClientBoatReservationsComponent } from './client/client-boat-reservations/client-boat-reservations.component';
import { OwnedBoatsComponent } from './owned-boats/owned-boats.component';
import { BoatEditComponent } from './boat-edit/boat-edit.component';
import { RegisterAdminComponent } from './register-admin/register-admin.component';
import { CottageOwnersComponent } from './cottage-owners/cottage-owners.component';
import { CottageOwnerDetailComponent } from './cottage-owner-detail/cottage-owner-detail.component';
import { BoatOwnersComponent } from './boat-owners/boat-owners.component';
import { BoatOwnerDetailComponent } from './boat-owner-detail/boat-owner-detail.component';
import { InstructorsComponent } from './instructors/instructors.component';
import { InstructorDetailComponent } from './instructor-detail/instructor-detail.component';
import { ClientsComponent } from './clients/clients.component';
import { ClientDetailComponent } from './client-detail/client-detail.component';
import { LoginNewAdminComponent } from './login-new-admin/login-new-admin.component';
import { ClientAdventureReservationsComponent } from './client/client-adventure-reservations/client-adventure-reservations.component';
import { ClientUpcomingReservationsComponent } from './client/client-upcoming-reservations/client-upcoming-reservations.component';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatBadgeModule} from '@angular/material/badge';
import {MatBottomSheetModule} from '@angular/material/bottom-sheet';
import {MatButtonModule} from '@angular/material/button';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatCardModule} from '@angular/material/card';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatChipsModule} from '@angular/material/chips';
import {MatStepperModule} from '@angular/material/stepper';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatDialogModule} from '@angular/material/dialog';
import {MatDividerModule} from '@angular/material/divider';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatListModule} from '@angular/material/list';
import {MatMenuModule} from '@angular/material/menu';
import {MatNativeDateModule, MatRippleModule} from '@angular/material/core';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatRadioModule} from '@angular/material/radio';
import {MatSelectModule} from '@angular/material/select';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatSliderModule} from '@angular/material/slider';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatSortModule} from '@angular/material/sort';
import {MatTableModule} from '@angular/material/table';
import {MatTabsModule} from '@angular/material/tabs';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatTreeModule} from '@angular/material/tree';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { SearchPipe } from './search.pipe';
import { CottageDetailOwnerComponent } from './cottage-detail-owner/cottage-detail-owner.component';
import { ClientOrdinaryReservationComponent } from './client/client-ordinary-reservation/client-ordinary-reservation.component';
import { ClientActionsComponent } from './client/client-actions/client-actions.component';
import { ClientSubscriptionsComponent } from './client/client-subscriptions/client-subscriptions.component';
import { ClientComplaintComponent } from './client/client-complaint/client-complaint.component';
import { ReservationHistoryComponent } from './reservation-history/reservation-history.component';
import { ReservationClientComponent } from './reservation-client/reservation-client.component';
import { ClientReviewComponent } from './client/client-review/client-review.component';
import { OwnedAdventuresComponent } from './owned-adventures/owned-adventures.component';
import { AdventureDetailOwnerComponent } from './adventure-detail-owner/adventure-detail-owner.component';
import { AdventureEditComponent } from './adventure-edit/adventure-edit.component';
import { BoatDetailOwnerComponent } from './boat-detail-owner/boat-detail-owner.component';
import { ReportComponent } from './report/report.component';
import { ImageSlideshowComponent } from './image-slideshow/image-slideshow.component';
import { ClientPenaltiesComponent } from './client/client-penalties/client-penalties.component';
import { AgmCoreModule } from '@agm/core';
import { BusinessReportComponent } from './business-report/business-report.component';
import { NgChartsModule } from 'ng2-charts';
import { FullCalendarModule } from '@fullcalendar/angular';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import { ReservationDialogComponent } from './reservation-dialog/reservation-dialog.component';
import { CalendarViewComponent } from './calendar-view/calendar-view.component';
import { LoyaltyProgramComponent } from './loyalty-program/loyalty-program.component';

FullCalendarModule.registerPlugins([
  dayGridPlugin,
  interactionPlugin
]);

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    AdventuresComponent,
    AdventureDetailComponent,
    CottagesComponent,
    CottageDetailComponent,
    RegistrationComponent,
    LoginComponent,
    ProfileComponent,
    SpecialRegistrationComponent,
    BoatsComponent,
    BoatDetailComponent,
    OwnedCottagesComponent,
    CottageEditComponent,
    ClientCottageReservationsComponent,
    ClientBoatReservationsComponent,
    OwnedBoatsComponent,
    BoatEditComponent,
    RegisterAdminComponent,
    CottageOwnersComponent,
    CottageOwnerDetailComponent,
    BoatOwnersComponent,
    BoatOwnerDetailComponent,
    InstructorsComponent,
    InstructorDetailComponent,
    ClientsComponent,
    ClientDetailComponent,
    LoginNewAdminComponent,
    ClientAdventureReservationsComponent,
    ClientUpcomingReservationsComponent,
    SearchPipe,
    CottageDetailOwnerComponent,
    ClientOrdinaryReservationComponent,
    ClientActionsComponent,
    ClientSubscriptionsComponent,
    ClientComplaintComponent,
    ReservationHistoryComponent,
    ReservationClientComponent,
    ClientReviewComponent,
    OwnedAdventuresComponent,
    AdventureDetailOwnerComponent,
    AdventureEditComponent,
    BoatDetailOwnerComponent,
    ReportComponent,
    ImageSlideshowComponent,
    ClientPenaltiesComponent,
    BusinessReportComponent,
    ReservationDialogComponent,
    CalendarViewComponent,
    LoyaltyProgramComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatAutocompleteModule,
    MatBadgeModule,
    MatBottomSheetModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatStepperModule,
    MatDatepickerModule,
    MatDialogModule,
    MatDividerModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatTreeModule,
    BrowserAnimationsModule,
    NgChartsModule,
    FullCalendarModule,
    AgmCoreModule.forRoot({

      apiKey: 'AIzaSyD9J8JKaQinAWb0q9usoTavpickUnpKHXg',

      libraries: ['places']

    }),
    CommonModule,
    NgbModalModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
