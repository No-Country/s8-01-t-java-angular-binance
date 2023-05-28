import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from 'src/app/services/auth.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { RegisterService } from 'src/app/services/register.service';
import { faAngleLeft, faExclamationCircle, faEyeSlash, faEye } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FontAwesomeModule, RouterModule],
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent {

  faAngleLeft = faAngleLeft;
  faExclamationCircle = faExclamationCircle;
  faEyeSlash = faEyeSlash;
  faEye = faEye;

  eyes = false;

  // password: 'password' | 'text' = 'password';

  form: any;
  
  step: number = 0;

  emailVerificated: boolean = false;
  
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private auth: AuthService,
    private registerService: RegisterService
    ) {
    this.form = this.formBuilder.nonNullable.group({
      email: ['', [Validators.email, Validators.required]],
      verificationCode: ['', [Validators.minLength(6), Validators.required]],
      password: ['', 
      [Validators.minLength(8), 
      Validators.maxLength(128), 
      Validators.required, 
      Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/)]
    ],
    });
  }

  singUp() {}

  // signUp() {
  //   this.auth.signUpEmail(this.form.getRawValue('email'))
  //   if (this.verificated) {
  //     this.auth.signUpPassword(this.form.getRawValue('password'))
  //   }
  // }

  // register() {
  //   if (this.form.valid) {
  //     this.status = 'loading';
  //     const { name, email, password } = this.form.getRawValue();
  //     this.authService.register(name, email, password)
  //     .subscribe({
  //       next: () => {
  //         this.status = 'success';
  //         this.router.navigate(['/login']);
  //       },
  //       error: (error) => {
  //         this.status = 'failed';
  //         console.log(error);
  //       }
  //     })
  //   } else {
  //     this.form.markAllAsTouched();
  //   }
  // }

  faEyes() {
    this.eyes = !this.eyes;
  }

  increaseStep() {
    this.step ++
  }

  decreaseStep() {
    this.step --
  }

  emailVerification() {
    // this.registerService.sendCodeVerification().subscribe(rta => {
    //   emailVerificated = rta;
    // })
  }

  sendCode() {
    if (this.emailVerificated) {
      this.increaseStep();
    }
  }

  signUp() {}

}
