import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  Validators,
  ReactiveFormsModule,
  FormGroup,
} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { RouterModule } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { first } from 'rxjs';
import Swal from 'sweetalert2';
import { faEyeSlash, faEye } from '@fortawesome/free-solid-svg-icons';
import { SpinnerComponent } from 'src/app/shared/spinner/spinner.component';

@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    FontAwesomeModule,
    SpinnerComponent,
  ],
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss'],
})
export class SignInComponent {
  form!: FormGroup;
  loading = false;
  submitted = false;
  step: number = 0;
  send: boolean = false;

  faEyeSlash = faEyeSlash;
  faEye = faEye;

  eyes = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private auth: AuthService
  ) {
    this.form = this.formBuilder.group({
      username: ['antonelaroccuzzo', [Validators.required]],
      password: ['password1234', [Validators.required]],
    });
  }

  signIn() {
    this.submitted = true;
    this.auth
      .signIn(this.form.value)
      .pipe(first())
      .subscribe({
        next: (res) => {
          const username = res.username;
          Swal.fire({
            title: `¡Hola! ${username}`,
            text: `Iniciaste sesión correctamente!`,
            icon: 'success',
            showConfirmButton: false,
            timer: 1000,
          }).then(() => {
            this.loading = true;
            this.router.navigate(['/dashboard']);
          });
        },
        error: (error) => {
          console.log('Error en el inicio de sesión:', error);
          Swal.fire({
            title: 'Error',
            text: 'Username o contraseña inválida',
            icon: 'error',
            confirmButtonText: 'Aceptar',
          });
        },
      });
  }

  faEyes() {
    this.eyes = !this.eyes;
  }

  increaseStep() {
    this.step++;
    this.send = true;
  }
}
