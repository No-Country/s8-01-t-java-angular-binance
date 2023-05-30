/* eslint-disable @typescript-eslint/no-inferrable-types */
import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { first } from 'rxjs';

import { SpinnerComponent } from 'src/app/shared/spinner/spinner.component';
import { FormErrors } from 'src/app/helpers/interfaces/form-errors.interface';

@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    HttpClientModule,
    SpinnerComponent,
  ],
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss'],
})
export class SignInComponent implements OnInit {
  private formBuilder = inject(FormBuilder);
  private authService = inject(AuthService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  form!: FormGroup;
  loading = false;
  submitted = false;
  step: number = 0;
  send: boolean = false;

  constructor() {
    // redirect to home if already logged in
    if (this.authService.userValue) {
      this.router.navigate(['private']);
    }
  }

  ngOnInit() {
    this.form = this.formBuilder.nonNullable.group({
      email: ['test1@mail.com', [Validators.required, Validators.email]],
      password: ['1234566', [Validators.required, Validators.minLength(6)]],
    });
  }

  // Obtiene el mensaje de error para un campo del formulario
  getErrorMessage(field: string): string {
    const control = this.form.get(field);
    if (control?.touched) {
      if (control?.errors) {
        const errorKey = Object.keys(control.errors)[0];
        return this.formErrors[errorKey];
      }
    }

    return '';
  }

  // Comprueba si un campo del formulario es inválido
  isFieldInvalid(field: string): boolean {
    const control = this.form.get(field)!;
    return (
      control?.hasError('required') ||
      control?.hasError('invalidEmail') ||
      (control?.hasError('invalidPassword') && control?.touched)
    );
  }

  // Errores de formulario
  formErrors: FormErrors = {
    required: 'Este campo es obligatorio',
    invalidEmail: 'El email no es válido',
    invalidPassword:
      'La contraseña debe tener al menos 8 caracteres, incluyendo una letra mayúscula, una letra minúscula, un número y un carácter especial',
  };

  increaseStep() {
    this.step++;
    this.send = true;
  }

  signIn() {
    // const { email, password } = this.form.value;

    // this.authService.login(email, password)
    //   .subscribe({
    //     next: () => this.router.navigateByUrl('/dashboard'),
    //     error: (message: string | undefined) => {
    //       Swal.fire('Error', message, 'error' )
    //     }
    //   })

    this.submitted = true;

    // Detener sim el formulario es invalido
    // if (this.form.invalid) {
    //   return;
    // }

    this.loading = true;
    this.authService
      .login(this.form.value)
      .pipe(first())
      .subscribe({
        next: () => {
          console.log('login success');
          // get return url from query parameters or default to home page
          const returnUrl =
            this.route.snapshot.queryParams['returnUrl'] || 'private';
          this.router.navigateByUrl(returnUrl);
        },
        error: () => {
          throw new Error('Error');
        },
      });
  }
}
