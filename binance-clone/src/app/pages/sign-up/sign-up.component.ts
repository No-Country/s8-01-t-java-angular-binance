import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from 'src/app/services/auth.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent {

  form: FormGroup;

  verificated: boolean = false;
  
  step: number = 0;
  
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private auth: AuthService
    ) {
    this.form = this.formBuilder.nonNullable.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.email, Validators.required]],
      password: ['', [Validators.minLength(6), Validators.required]],
      code
      
    });
  }

  // Hay al menos 3 formularios
  // Primero: email

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

  increaseStep() {
    this.step ++
  }

}
