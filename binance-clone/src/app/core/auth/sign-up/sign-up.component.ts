import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from 'src/app/services/auth.service';
import { AbstractControl, FormBuilder, FormsModule, ReactiveFormsModule, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { faAngleLeft, faExclamationCircle, faEyeSlash, faEye } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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
  formCode: any;
  
  step: number = 0;
  
  emailVerificated: boolean = false;
  
  constructor(
    private http: HttpClient,
    private formBuilder: FormBuilder,
    private router: Router,
    private auth: AuthService,
    ) {
    this.form = this.formBuilder.nonNullable.group({
      email: ['', [
        Validators.email, 
        Validators.required, 
        this.NoDoubleAtValidator()
      ]],
      password: ['', [
        Validators.minLength(8), 
        Validators.maxLength(128), 
        Validators.required, 
        this.caracterEspecialValidator(),
        this.letraMinusculaValidator(),
        this.letraMayusculaValidator(),
        this.numeroValidator()
      ]],
      nationality: ['', [Validators.required, Validators.maxLength(128)]],
      legalName: ['', [Validators.required, Validators.maxLength(128)]],
      legalLastName: ['', [Validators.required, Validators.maxLength(128)]],
      birthdate: ['', [Validators.required]],
      fullAddress: ['', [Validators.required, Validators.maxLength(128)]],
      zipCode: ['', [Validators.required, Validators.maxLength(20), Validators.pattern('^[0-9]*$')]],
      city: ['', [Validators.required, Validators.maxLength(128)]],
      country: ['', [Validators.required, Validators.maxLength(128)]],
      username: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(20)]],
      agree: [false, [Validators.requiredTrue]]
    });

    this.formCode = this.formBuilder.nonNullable.group({
      verificationCode: ['', [
        Validators.required
      ]]})
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

    )
  }


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
  code: any;
  num: any;
  disabled: boolean = false;
  
  verificarCodigo() {

    this.code = this.formCode.getRawValue();
    console.log('this.code', this.code )

    if (this.code.verificationCode === this.num) {
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

  enviarCodigoPorEmail() {
    const url = `${this.apiUrl}/auth/email`;
    const email = this.form.get('email').value;
    const codigo = this.generateRandomNumber();
    this.num = codigo;
    console.log('this num', this.num)
    console.log('codigo',codigo)

    const headers = new HttpHeaders().set('Accept', '*/*');
  
    this.http.post(url, null, {
      headers: headers,
      params: {
        email: email,
        num: codigo.toString()
      }
    })
    .subscribe(
      respuesta => {
        console.log('respuesta post código de verificación',respuesta);
      },
      error => {
        console.error(error);
      }
    );
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


  NoDoubleAtValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const value: string = control.value;
      if (value && value.includes('@@')) {
        return { noDoubleAt: true };
      }
      return null;
    };
  }

  NumberValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const value: string = control.value;
      if (value && !/^\d+$/.test(value)) {
        return { onlyNumbers: true };
      }
      return null;
    };
  }

  // Validators para password

  letraMinusculaValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const patron = /[a-z]/;
      const valid = patron.test(control.value);
      return valid ? null : { letraMinuscula: true };
    };
  }
  
  letraMayusculaValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const patron = /[A-Z]/;
      const valid = patron.test(control.value);
      return valid ? null : { letraMayuscula: true };
    };
  }
  
  numeroValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const patron = /\d/;
      const valid = patron.test(control.value);
      return valid ? null : { numero: true };
    };
  }
  
  caracterEspecialValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const patron = /[@#$%^&+=!]/;
      const valid = patron.test(control.value);
      return valid ? null : { caracterEspecial: true };
    };
  }

}
