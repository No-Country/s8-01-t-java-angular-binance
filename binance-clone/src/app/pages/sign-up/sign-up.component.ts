import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from 'src/app/services/auth.service';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { faAngleLeft, faExclamationCircle, faEyeSlash, faEye } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ButtonComponent } from 'src/app/components/button/button.component';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { BehaviorSubject } from 'rxjs';
import { Register } from 'src/interfaces/register.model';
// import { LocalStorageService } from 'ngx-webstorage';


@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FontAwesomeModule, RouterModule, FormsModule],
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  private cambiosLocalStorage = new BehaviorSubject<Register | null>(null);

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
    // private localStorageService: LocalStorageService

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

  registro: any;

  ngOnInit(): void {
    this.cambiosLocalStorage.subscribe(() => {
      this.obtenerRegistro();
    });
  }

  singUp() {
    
    this.auth.signUp(this.form.getRawValue())
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

  
  signUp() {}
  
  
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
  

    this.randomNumber = Number(this.generateRandomNumber());
    this.num = this.randomNumber;
    console.log('random number',this.randomNumber)
    const email = this.form.get('email').value;

  
    const data = {
      email: email,
      num: this.randomNumber
    };
    console.log(data)
  
    this.http.post(`${this.apiUrl}/api/v1/auth/email`, data).subscribe(
      response => {
        // Manejar la respuesta de la API
        console.log('email verification response',response);
      },
      error => {
        // Manejar el error de la API
        console.error('email verification', error);
      }
    );

    const num = this.form.get('verificationCode').value;
    if(num === this.randomNumber){

    }
  }


  // guardarRegistro() {
  //   const registro = this.form.getRawValue();
  //   this.localStorageService.store('miRegistro', registro);
  // }

  // obtenerRegistro() {
  //   const registro = this.localStorageService.retrieve('miRegistro');
  //   console.log(registro);
  // }

  guardarRegistro() {
    const registro = this.form.getRawValue();
    localStorage.setItem('miRegistro', JSON.stringify(registro));
  }

  
  obtenerRegistro() {
    const registroString = localStorage.getItem('miRegistro');
    if (registroString) {
      const registro = JSON.parse(registroString);
      this.registro = registro;
      console.log('soy miRegistro',registro);
    } else {
      console.log('No se encontró ningún registro en el almacenamiento local.');
    }
  }



}
