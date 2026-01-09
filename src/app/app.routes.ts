import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { EventListing } from './pages/event-listing/event-listing';
import { About } from './pages/about/about';
import { ContactUs } from './pages/contact-us/contact-us';
import { RegistrationComponent } from './pages/registration/registration';

export const routes: Routes = [
    { path: '', component: Home },
    { path: 'events', component: EventListing },
    { path: 'about', component: About },
    { path: 'contact', component: ContactUs },
    { path: 'Login', component: RegistrationComponent },
    //{ path: 'events/:id', component: EventDetails },
    // { path: 'create-event', component:  },
    // { path: 'login', component: LoginComponent },
];
