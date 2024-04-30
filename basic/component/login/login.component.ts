import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';
import { NzNotificationComponent, NzNotificationService } from 'ng-zorro-antd/notification';
import { UserStorageService } from '../../services/storage/user-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  validateForm! : FormGroup;

  constructor(private fb : FormBuilder,
              private authservice: AuthService,
              private router: Router,
              private notification: NzNotificationService
  ){
    this.validateForm = this.fb.group({
      // userName: new FormControl("",[Validators.email,Validators.required]),
      userName: [null, [Validators.email,Validators.required]],
      password : new FormControl("",[Validators.required]),
  })
  }

  ngOnInIt(){
   
  }

  submitForm(){
    this.authservice.login(this.validateForm.get(['userName'])!.value,this.validateForm.get(['password'])!.value)
        .subscribe( res =>{
         console.log(res);
         if(UserStorageService.isClientLoggedIn()){
          this.router.navigateByUrl('client/dashboard')
         }else if(UserStorageService.isCompanyLoggedIn()){
          this.router.navigateByUrl('company/dashboard')
         }
         },
          error => {
            this.notification
            .error(
              'ERROR',
              `Bad Credentials`,
              { nzDuration: 5000 }
            );
          }
      );
  }
}
