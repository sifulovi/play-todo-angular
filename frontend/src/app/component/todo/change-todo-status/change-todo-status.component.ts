import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {TodoService} from '../../../service/todo.service';
import {NzNotificationService} from 'ng-zorro-antd/notification';

@Component({
  selector: 'app-change-todo-status',
  templateUrl: './change-todo-status.component.html',
  styleUrls: ['./change-todo-status.component.scss']
})
export class ChangeTodoStatusComponent implements OnInit {

  @Input() public isShowModal = false;
  @Input() public todoId = false;
  isOkLoading = false;
  @Output() modalEmitter = new EventEmitter();
  validateForm: FormGroup;
  image: any;
  selectedFile: File;
  date: null;
  todo: any = {};


  constructor(private notification: NzNotificationService,
              private router: Router,
              private fb: FormBuilder,
              private service: TodoService) {
  }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      todoStatus: [null, [Validators.required]],
    });
  }


  submitForm(): void {
    // tslint:disable-next-line:forin
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }


    const payload = {
      ...this.validateForm.value
    };
    this.service.updateTodoStatus(payload, this.todoId).subscribe(
      res => {
        console.log(res);
        this.notification.create(
          'success',
          'Todo successfully update',
          ''
        );
        window.location.href = 'todo';
      },
      err => {
        console.log(err);
      }
    );
  }


  handleCancel(): void {
    this.handleModalEmitter();
  }

  handleOk(): void {
    this.isOkLoading = true;
    this.handleModalEmitter();
  }

  handleModalEmitter(): void {
    this.isShowModal = false;
    this.modalEmitter.emit(this.isShowModal);
  }

  onFileChanged(event): void {
    this.selectedFile = event.target.files[0];
  }


  onChange(result: Date): void {
    console.log('onChange: ', result);
  }

}
