import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'notebooks',
    pathMatch: 'full',
  },

  {
    path: 'notebooks',
    loadComponent: () =>
      import('./features/notebooks/pages/lista/lista.component').then(
        (m) => m.ListaComponent
      ),
  },

  {
    path: 'notebooks/cadastrar',
    loadComponent: () =>
      import('./features/notebooks/pages/cadastro/cadastro.component').then(
        (m) => m.CadastroComponent
      ),
  },
];
