import { inject } from '@angular/core';
import { CanActivateFn, } from '@angular/router';
import { UserAuthService } from '../services/user-auth.service';

export const loginredirectGuard: CanActivateFn = (route, state) => {
  const auth = inject(UserAuthService);
  return !auth.getLoginStatus();
};
