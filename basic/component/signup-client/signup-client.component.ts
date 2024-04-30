import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup-client',
  templateUrl: './signup-client.component.html',
  styleUrls: ['./signup-client.component.scss']
})
export class SignupClientComponent {

  validateForm!:FormGroup

  constructor(private fb: FormBuilder,
              private authService : AuthService,
              private notification : NzNotificationService,
              private router: Router){

                this.validateForm = this.fb.group({
                  email: new FormControl("",[Validators.email,Validators.required]),
                  name : new FormControl("",[Validators.required]),
                  lastname : new FormControl("",[Validators.required]),
                  phone : new FormControl(""),
                  password : new FormControl("",[Validators.required]),
                  checkPassword : new FormControl ("",[Validators.required])
                });
              }

  ngOnInIt(){
    // this.validateForm = this.fb.group({
    //   email: new FormControl("",[Validators.email,Validators.required]),
    //   name : new FormControl("",[Validators.required]),
    //   lastname : new FormControl("",[Validators.required]),
    //   phone : new FormControl(""),
    //   password : new FormControl("",[Validators.required]),
    //   checkPassword : new FormControl ("",[Validators.required])
    // }); when this code was here so we got error saying formgroup expect a formgroup instance so the code is run in constructor
  }


submitForm() {
  this.authService.registerClient(this.validateForm.value).subscribe(
    data => {
        this.notification.success(
          'SUCCESS',
          'Signup successful',
          { nzDuration: 5000 }
        );
        this.router.navigateByUrl('/login');
      },
      error => {
        this.notification.error(
          'ERROR',
          `${error.error}`,
          { nzDuration: 5000 }
      );
    }
  );
}

  
}
