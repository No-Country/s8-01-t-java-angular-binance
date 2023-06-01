import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, Validators, ReactiveFormsModule, } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { RouterModule } from '@angular/router';

interface Registro {
  username: string;
  password: string;
}

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
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  increaseStep() {
    this.step ++
  }

  signIn(){
    // if(this.form.valid) {
    //   const { username, password } = this.form.getRawValue();
    //   this.auth.signIn(username, password)
    //   this.router.navigate(['/dashboard'])
    // }



    
  }


  ngOnInit(): void {
    this.obtenerRegistro();
  }


  registro: Registro | null = null;

  obtenerRegistro() {
    const registroString = localStorage.getItem('miRegistro');
    if (registroString) {
      const registro = JSON.parse(registroString) as Registro;
      this.registro = registro;
      console.log('Soy miRegistro:', registro);
      const { username, password } = registro;
      if(this.form.username === username && this.form.password === password) {
        this.auth.signIn(password, username)
      }
      console.log('Username:', username);
      console.log('Password:', password);
    } else {
      console.log('No se encontró ningún registro en el almacenamiento local.');
    }
  }
}
