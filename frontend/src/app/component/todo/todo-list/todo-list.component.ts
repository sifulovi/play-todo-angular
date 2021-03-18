import {Component, OnInit} from '@angular/core';
import {TodoService} from '../../../service/todo.service';
import {NzNotificationService} from 'ng-zorro-antd/notification';

@Component({
  selector: 'app-assignment-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.scss']
})
export class TodoListComponent implements OnInit {

  todoList = [];
  isShowModalForCreate = false;
  isShowModalForComplete = false;
  todoId = '';

  constructor(private notification: NzNotificationService,
              public service: TodoService) {
  }

  ngOnInit(): void {
    this.service.getTodoList().subscribe(
      res => {
        this.todoList = res;
        console.log(this.todoList);
      }
      , err => {
        console.log(err);
      }
    );
  }

  showModal(): void {
    this.isShowModalForCreate = true;
  }

  showModalForComplete(id): void {
    this.todoId = id;
    this.isShowModalForComplete = true;
    // this.isShowModalForCreate = true;
  }

  delete(id: any): void {
    this.service.deleteTodo(id).subscribe(
      res => {
        this.ngOnInit();
        this.notification.create(
          'success',
          'A Todo is deleted successfully',
          ''
        );
      }
      , err => {
        console.log(err);
      }
    );
  }
}
