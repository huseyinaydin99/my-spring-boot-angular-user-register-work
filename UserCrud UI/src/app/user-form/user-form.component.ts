import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { AlertifyService } from '../alertify.service';
import { UserServiceService } from '../user-service.service';
import { User } from './model/User';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {
  userAddForm: FormGroup;
  model: User;
  model2: User;
  users: User[];
  index: number = 0;
  constructor(
    private formBuilder: FormBuilder,
    private userService: UserServiceService,
    private alertifyService: AlertifyService) {

  }


  ngOnInit(): void {

    this.index = 0;
    this.model = new User();
    this.users = new Array<User>();
    this.getAllUser();
    this.init();
    this.index--;
    this.model2 = this.users[this.index]
    this.userAddForm = this.formBuilder.group({
      userId: ["", Validators.required],
      tckn: ["", Validators.required],
      eMail: ["", Validators.required],
      password: ["", Validators.required],
      gsm: ["", Validators.required],
      address: ["", Validators.required]
    });

  }

  get f(){

    return this.userAddForm.controls;

  }

  add() {
    if (this.userAddForm.valid) {
      this.model = Object.assign({}, this.userAddForm.value);
      this.userService.addUser(this.model).subscribe(data => {
        this.alertifyService.success("Ekleme işlemi başarılı");
        this.getAllUser();
      });
    }
  }

  delete() {
    this.model = Object.assign({}, this.userAddForm.value);
    if (this.model.userId) {
        this.userService.deleteUser2(this.model.userId).subscribe(data => {
          this.alertifyService.success("Silme işlemi başarılı!");
          this.getAllUser();
        });
    }
    else {
      this.alertifyService.success("Silmek için kullanıcı ID'si giriniz!");
    }
  }

  update() {
    if (this.userAddForm.valid) {
      this.model = Object.assign({}, this.userAddForm.value);
      this.userService.updateUser(this.model).subscribe(data => {
        this.alertifyService.success("Başarı ile güncellendi " + this.model);
        this.getAllUser();
      });
    }
    else {
      this.alertifyService.success("Güncelleme için lütfen formu tamamen doldurunuz!");
    }
  }

  getAllUser() {
    this.users = [];
    this.userService.getAllUser().subscribe(data => {
      console.log("datamız " + data);
      for (const d of (data as any[])) {
        this.users.push({
          userId: d.userId,
          tckn: d.tckn,
          eMail: d.eMail,
          password: d.password,
          gsm: d.gsm,
          address: d.address
        });
      }
      console.log(this.users);
    });
  }

  init() {
    this.model2 = this.users[this.index];
  }

  nextUser() {
    
    this.index++;
    if(this.index>=this.users.length){
      this.index--;
    }
    this.model2 = this.users[this.index];
  }

  backUser() {
    if(this.index <= 0)
      this.index = 1;
    this.index--;
    this.model2 = this.users[this.index];
  }

  clearField(){
    this.model2 = new User();
  }

}
