import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from "@angular/common/http";
import { Observable,throwError } from 'rxjs';
import { tap,catchError } from 'rxjs/operators';
import { User } from './user-form/model/User';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  path: string = "http://localhost:8080/api";
  constructor(private http: HttpClient) { }

  getUser(userId: number){
    let newPath = this.path;
    if(userId){
      newPath += "/id/"+userId;
    }

    return this.http.get(newPath);
  }
  handleError(err: HttpErrorResponse) {
    let errorMessage = '';
    if(err.error instanceof ErrorEvent){
      errorMessage = "Bir hata oluştu hata: " + err.error.message;
    }
    else{
      errorMessage = "Sistemsel bir hata";
    }
    return throwError(errorMessage);
  }

  addUser(user: User):Observable<User>{
    const httpOptions ={
      headers: new HttpHeaders({
        'Content-Type':'application/json',
        'Authorization': 'Token'
      })
    };

    return this.http.post<User>(this.path+"/add",user,httpOptions).pipe(
      tap(data=>console.log(JSON.stringify(data)),
      catchError(this.handleError)
    ));
  }

  deleteUser():Observable<User>{
    const httpOptions ={
      headers: new HttpHeaders({
        'Content-Type':'application/json',
        'Authorization': 'Token'
      })
    };

    return this.http.delete<User>(this.path + "/delete");
  }

  deleteUser2(id: number):Observable<User>{
    const httpOptions ={
      headers: new HttpHeaders({
        'Content-Type':'application/json',
        'Authorization': 'Token'
      })
    };

    return this.http.delete<User>(this.path + "/delete/" + id);
  }

  updateUser(user: User):Observable<User>{
    const httpOptions ={
      headers: new HttpHeaders({
        'Content-Type':'application/json',
        'Authorization': 'Token'
      })
    };

    return this.http.put<User>(this.path + "/update", user)
  }

  getUser2(id: number):Observable<User>{
    return this.http.get<User>(this.path + "getId/" + id);
  }

  getAllUser(){
    return this.http.get(this.path + "/getAll");
  }
}
