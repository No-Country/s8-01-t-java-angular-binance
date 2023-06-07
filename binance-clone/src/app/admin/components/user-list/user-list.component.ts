import { AdminService } from './../../services/admin.service';
import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { User } from 'src/app/helpers/interfaces';
import { Router } from '@angular/router';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Modal, Ripple, initTE, Input } from 'tw-elements';
import { ModalUserComponent } from 'src/app/shared/modal-user/modal-user.component';
import { SpinnerService } from 'src/app/shared/spinner/services/spinner.service';
import { SpinnerComponent } from 'src/app/shared/spinner/spinner.component';

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [
    CommonModule,
    ModalUserComponent,
    HttpClientModule,
    SpinnerComponent,
  ],
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss'],
})
export class UserListComponent implements OnInit {
  users: User[] = [];
  loading: boolean = false;

  constructor(
    private adminService: AdminService,
    private http: HttpClient,
    private router: Router,
    private readonly dialog: MatDialog,
    public spinnerService: SpinnerService
  ) {}

  ngOnInit() {
    this.adminService.getAll().subscribe((users) => {
      this.users = users;
    });

    initTE({ Modal, Ripple, Input });

    this.spinnerService.show();
    this.spinnerService.startTimer(3000);
    setTimeout(() => {
      this.loading = true;
    }, 1000);
  }

  detailUser(dbUser: User) {
    const dialogConfig: MatDialogConfig = {
      data: { ...dbUser },
      panelClass: 'custom-modalbox',
    };

    this.dialog.open(ModalUserComponent, dialogConfig);
  }

  sendId(id: string): void {
    this.router.navigate(['/users', id]);
  }
}
