import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {TodoRoutingModule} from './todo-routing.module';
import {TodoListComponent} from './todo-list/todo-list.component';
import {AntSharedModule} from '../../ant-shared.module';
import {CreateTodoComponent} from './create-todo/create-todo.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ChangeTodoStatusComponent} from './change-todo-status/change-todo-status.component';


@NgModule({
  declarations: [
    TodoListComponent,
    CreateTodoComponent,
    ChangeTodoStatusComponent
  ],
  exports: [
    CreateTodoComponent
  ],
  imports: [
    CommonModule,
    TodoRoutingModule,
    AntSharedModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class TodoModule {
}
