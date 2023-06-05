import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from 'src/app/services/auth.service';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { faAngleLeft, faExclamationCircle, faEyeSlash, faEye } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { first } from 'rxjs';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FontAwesomeModule, RouterModule, FormsModule],
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent {

  apiUrl = environment.API_URL;


  faAngleLeft = faAngleLeft;
  faExclamationCircle = faExclamationCircle;
  faEyeSlash = faEyeSlash;
  faEye = faEye;

  eyes = false;
  
  form: any;
  
  step: number = 0;
  
  emailVerificated: boolean = false;
  
  constructor(
    private http: HttpClient,
    private formBuilder: FormBuilder,
    private router: Router,
    private auth: AuthService,

    ) {
    this.form = this.formBuilder.nonNullable.group({
      email: ['', [Validators.email, Validators.required]],
      // verificationCode: ['', [
      //   Validators.minLength(6), 
      //   Validators.maxLength(6), 
      //   Validators.required, 
      //   Validators.pattern('^[0-9]*$')]],
      password: ['', [
        Validators.minLength(8), 
        Validators.maxLength(128), 
        Validators.required, 
        // Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\s\S])[A-Za-z\d\s\S]{8,}$/)
      ]],
      nationality: ['', [Validators.required]],
      legalName: ['', [Validators.required]],
      legalLastName: ['', [Validators.required]],
      birthdate: ['', [Validators.required]],
      fullAddress: ['', [Validators.required]],
      zipCode: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
      city: ['', [Validators.required]],
      country: ['', [Validators.required]],
      username: ['', [Validators.required]],
      agree: [false, [Validators.requiredTrue]]
    });
  }


  signUp() {
    const register = this.form.getRawValue();
    console.log('register desde sign-up getRawValue()',register)
    
    this.auth.signUp(this.form.value)
    .subscribe(register => {
      console.log('register desde sign-up',register)

    this.auth.signUp(register).subscribe(
      (response) => {
        console.log('Registro exitoso', response);
      },
      (error) => {
        console.error('Error en el registro', error);
      }
    );
    }



    //   {
    //   next: (res) => {
    //     const username = res.username;
    //     Swal.fire({
    //       title: `¡Hola! ${username}`,
    //       text: `Iniciaste sesión correctamente!`,
    //       icon: 'success',
    //       showConfirmButton: false,
    //       timer: 3000
    //     }).then(() => {
    //       this.router.navigate(['/login']);
    //     });
    //   },
    //   error: (error) => {
    //     console.log('Error en el inicio de sesión:', error);
    //     Swal.fire({
    //       title: 'Error',
    //       text: 'Username o contraseña inválida',
    //       icon: 'error',
    //       confirmButtonText: 'Aceptar'
    //     });
    //   }
    // }
    );
  }

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

  
  
  toggleButton = false;
  
  clickToggleButton(){
    this.toggleButton = !this.toggleButton;
  }
  
  

  
  email: string = '';
  randomNumber: number = 0;
  codeSent: Number | null = null;
  num: number = 0;
  
  sendCode() {

    this.codeSent = this.form.get('verificationCode').value;

    if (this.codeSent === this.num) {
      this.increaseStep();
    }
  }
  generateRandomNumber(): string {
    const length = 6;
    let randomNumber = '';
    const characters = '0123456789';
  
    for (let i = 0; i < length; i++) {
      const index = Math.floor(Math.random() * characters.length);
      randomNumber += characters.charAt(index);
    }
  
    return randomNumber;
  }
  
  emailVerification() {
  

    // this.randomNumber = Number(this.generateRandomNumber());
    // this.num = this.randomNumber;
    // console.log('random number',this.randomNumber)
    // const email = this.form.get('email').value;

  
    // const data = {
    //   email: email,
    //   num: this.randomNumber
    // };
    // console.log(data)
  
    // this.http.post(`${this.apiUrl}/api/v1/auth/email`, data).subscribe(
    //   response => {
    //     // Manejar la respuesta de la API
    //     console.log('email verification response',response);
    //   },
    //   error => {
    //     // Manejar el error de la API
    //     console.error('email verification', error);
    //   }
    // );

    // const num = this.form.get('verificationCode').value;
    // if(num === this.randomNumber){

    // }
  }





}
