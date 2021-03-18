import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {TodoService} from '../../service/todo.service';
import {NzNotificationService} from 'ng-zorro-antd/notification';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  validateForm!: FormGroup;


  constructor(
    private notification: NzNotificationService,
    private  router: Router, private fb: FormBuilder, private service: TodoService) {
  }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      username: [null, [Validators.required]],
      password: [null, [Validators.required]],
    });
  }

  submitForm(): void {
    // tslint:disable-next-line:forin
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }

    this.service.login(this.validateForm.value).subscribe(
      res => {
        localStorage.setItem('auth', JSON.stringify(res));
        console.log(res);
        window.location.href = 'todo';
      },
      err => {

        this.notification.create(
          'error',
          'Username or password is incorrect',
          ''
        );

        console.log(err);
      }
    );
  }


  login(): void {

  }

}
