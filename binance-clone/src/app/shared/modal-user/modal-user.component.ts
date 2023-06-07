import { Component, Inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalComponent } from '../modal/modal.component';
import { Router } from '@angular/router';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Modal, Ripple, initTE, Input } from 'tw-elements';
import { User } from 'src/app/helpers/interfaces';
import { AdminService } from 'src/app/admin/services/admin.service';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  Validators,
} from '@angular/forms';

@Component({
  selector: 'app-modal-user',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './modal-user.component.html',
})
export class ModalUserComponent implements OnInit {
  userData!: User;

  constructor(
    private router: Router,
    private adminService: AdminService,
    private fb: FormBuilder,
    public readonly dialogRef: MatDialogRef<ModalComponent>,
    @Inject(MAT_DIALOG_DATA) public readonly dbUser: User
  ) {
    this.userData = {
      id: dbUser.id,
      email: dbUser.email,
      username: dbUser.username,
      legalName: dbUser.legalName,
      legalLastName: dbUser.legalLastName,
      walletId: dbUser.walletId,
    };
  }

  ngOnInit() {
    initTE({ Modal, Ripple, Input });
  }

  closeDetail() {
    this.dialogRef.close({ ...this.dbUser });
  }

  updateUser(id: number, data: User) {
    this.adminService.updateUser(id, data).subscribe(
      (response) => {
        console.log('Usuario actualizado:', response);
        alert(`Usuario actualizado: ${response}`);
      },
      (error) => {
        console.error('Error al actualizar el usuario:', error);
      }
    );
  }
}
