import { Component, OnInit } from '@angular/core';

import { CommonModule } from '@angular/common';

import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';

import { NotebookService } from '../../notebook.service';
import { Notebook } from '../../models/notebook.model';
import { Router, RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-lista',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatChipsModule,
    RouterOutlet,
    RouterLink,
  ],
  templateUrl: './lista.component.html',
  styleUrl: './lista.component.scss',
})
export class ListaComponent implements OnInit {
  notebooks: Notebook[] = [];

  displayedColumns = ['nome', 'descricao', 'preco', 'acessorios', 'acoes'];

  constructor(private service: NotebookService, private router: Router) {}

  ngOnInit(): void {
    this.carregar();
  }

  carregar() {
    this.service.listarComAcessorios().subscribe({
      next: (dados) => {
        this.notebooks = dados;
      },

      error: (erro) => {
        console.error('Erro ao carregar notebooks', erro);
      },
    });
  }

  editar(id?: number) {
    debugger
    if (!id) {
      return;
    }

    this.router.navigate(['/notebooks/cadastrar', id]);
  }

  excluir(id?: number) {
    if (!id) {
      return;
    }

    this.service.excluir(id).subscribe({
      next: () => {
        this.carregar();
      },

      error: (erro) => {
        console.error('Erro ao excluir notebook', erro);
      },
    });
  }
}
