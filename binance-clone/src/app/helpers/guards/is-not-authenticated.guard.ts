import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

import { AuthStatus } from '../interfaces';
import { AuthService } from 'src/app/services/auth.service';

// PublicGuard - PrivateGuard

export const isNotAuthenticatedGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  return true;
};
