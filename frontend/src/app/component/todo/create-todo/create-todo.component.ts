import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {TodoService} from '../../../service/todo.service';
import {Router} from '@angular/router';
import {NzNotificationService} from 'ng-zorro-antd/notification';

@Component({
  selector: 'app-create-todo',
  templateUrl: './create-todo.component.html',
  styleUrls: ['./create-todo.component.scss']
})
export class CreateTodoComponent implements OnInit {

  @Input() public isShowModal = false;
  isOkLoading = false;
  @Output() modalEmitter = new EventEmitter();
  validateForm: FormGroup;
  teacherName = '';
  image: any;
  assignmentImage: File;
  teacherImage: any;
  subjectImage: any;

  date: null;

  constructor(private notification: NzNotificationService,
              private router: Router,
              private fb: FormBuilder,
              private service: TodoService) {
  }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      todoTitle: [null, [Validators.required]],
      todoDescription: [null, [Validators.required]],
      todoStatus: [null, [Validators.required]],
    });
  }


  submitForm(): void {
    // tslint:disable-next-line:forin
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }


    this.service.saveTodo(this.validateForm.value).subscribe(
      res => {
        console.log(res);
        this.notification.create(
          'success',
          'A Todo Save successfully',
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


}
