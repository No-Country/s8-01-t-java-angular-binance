import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from 'src/app/services/auth.service';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { RegisterService } from 'src/app/services/register.service';
import { faAngleLeft, faExclamationCircle, faEyeSlash, faEye } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ButtonComponent } from 'src/app/components/button/button.component';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FontAwesomeModule, RouterModule, FormsModule],
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
  checkboxActivated: boolean = false;
  
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private auth: AuthService,
    private registerService: RegisterService
    ) {
    this.form = this.formBuilder.nonNullable.group({
      email: ['', [Validators.email, Validators.required]],
      verificationCode: ['', [
        Validators.minLength(6), 
        Validators.maxLength(6), 
        Validators.required, 
        Validators.pattern('^[0-9]*$')]],
      password: ['', [
        Validators.minLength(8), 
        Validators.maxLength(128), 
        Validators.required, 
        Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\s\S])[A-Za-z\d\s\S]{8,}$/)]],
      nationality: ['', [Validators.required]],
      legalName: ['', [Validators.required]],
      legalLastName: ['', [Validators.required]],
      birthdate: ['', [Validators.required]],
      fullAddress: ['', [Validators.required]],
      postalCode: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
      city: ['', [Validators.required]],
      country: ['', [Validators.required]],
      roles: ['USER'],
      username: ['', [Validators.required]],
      checkbox: [false, [Validators.requiredTrue]]
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

  disabled = false;

  checkbox(){
    if(this.checkboxActivated === true) {
      this.form.checkbox = true;
      this.increaseStep();
    } else {
      this.disabled = false;
    }
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


  toggleButton = false;

  clickToggleButton(){
    this.toggleButton = !this.toggleButton;
  }

  toggleCheckbox(){
    this.checkboxActivated = !this.checkboxActivated;
  }

}
