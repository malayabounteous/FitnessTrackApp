import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './components/auth/login/login';
import { RegisterComponent } from './components/auth/register/register';
import { HomeComponent } from './components/home/home';
import { DashboardComponent } from './components/dashboard/dashboard/dashboard';
import { ProfileComponent } from './components/profile/profile/profile';
import { AddMealComponent } from './components/meal/add-meal/add-meal';
import { AddWorkoutComponent } from './components/workout/add-workout/add-workout';
import { GoalAddComponent } from './components/goal-add/goal-add';
import { MealLogsComponent } from './components/meal/meal-list/meal-list';
import { WorkoutListComponent } from './components/workout/workout-list/workout-list';
import { GoalComponent } from './components/goal/goal';
import {RecommendationComponent} from './components/recommendation/recommendation/recommendation';
import { ProgressComponent } from './components/progress/progress-chart/progress-chart';
import { guestGuard } from './guards/guest-guard';
import { authGuard } from './guards/auth-guard';
export const routes: Routes = [

  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent , canActivate: [guestGuard]},
  { path: 'register', component: RegisterComponent , canActivate: [guestGuard]},
  { path: 'dashboard', component: DashboardComponent , canActivate: [authGuard]},
  { path: 'profile', component: ProfileComponent , canActivate: [authGuard]},
  { path: 'add-meal', component: AddMealComponent , canActivate: [authGuard]},
  { path: 'add-workout', component: AddWorkoutComponent , canActivate: [authGuard]},
  { path: 'add-goal', component: GoalAddComponent , canActivate: [authGuard]},
  {path:'meals', component: MealLogsComponent},
  {path:'workouts', component: WorkoutListComponent},
  {path:'goals', component: GoalComponent},
  {path:'recommendation', component: RecommendationComponent},
  {path:'progress', component: ProgressComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}