import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './component/login/login.component';
import {AssignmentGuard} from './interceptor/assignment.guard';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: '', component: LoginComponent},
  {path: 'login', pathMatch: 'full', redirectTo: '', component: LoginComponent},
  {
    path: 'todo',
    canActivate: [AssignmentGuard],
    loadChildren: () => import('./component/todo/todo.module').then(m => m.TodoModule)
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
