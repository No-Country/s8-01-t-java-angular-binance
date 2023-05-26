import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, Validators, ReactiveFormsModule, } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent {
  form: any;

  step: number = 0;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private auth: AuthService
    ) {
    this.form = this.formBuilder.nonNullable.group({
      email: ['', [Validators.email, Validators.required]],
      passowrd: ['', [Validators.minLength(6), Validators.required]]
    });
  }

  increaseStep() {
    this.step ++
  }

  signIn(){}
}
