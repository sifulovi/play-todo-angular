import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  API = 'http://localhost:9000';

  constructor(private http: HttpClient) {
  }


  login(payload): any {
    return this.http.post(`${this.API}/login`, payload);
  }


  getTodoList(): any {
    return this.http.get(`${this.API}/todo`);
  }


  saveTodo(data): any {
    return this.http.post(`${this.API}/todo`, data);
  }

  updateTodoStatus(data, id): any {
    return this.http.put(`${this.API}/todo-status-update/${id}`, data);
  }

  logout(): any {
    localStorage.removeItem('auth');
    localStorage.clear();
  }

  getData(): any {
    const asd = JSON.parse(localStorage.getItem('auth'));
    return asd !== null;
  }

  isAdminUser(): any {
    const user = JSON.parse(localStorage.getItem('auth'));
    return user.userRole === 'ADMIN_USER';
  }

  getCommonUser(): any {
    const user = JSON.parse(localStorage.getItem('auth'));
    return user.userRole === 'COMMON_USER';
  }


  deleteTodo(id): any {
    return this.http.delete(`${this.API}/todo/${id}`);
  }


}
