import { Component } from '@angular/core';

import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';

import { CommonModule } from '@angular/common';

import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatTableModule } from '@angular/material/table';

import { NotebookService } from '../../notebook.service';

import { Notebook, Acessorio } from '../../models/notebook.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cadastro',
  standalone: true,

  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatDividerModule,
    MatTableModule,
  ],

  templateUrl: './cadastro.component.html',
  styleUrl: './cadastro.component.scss',
})
export class CadastroComponent {
  mostrarCadastroAcessorio = false;
  acessorios: Acessorio[] = [];
  displayedColumns: string[] = ['nome', 'descricao', 'preco', 'acao'];
  notebookForm: FormGroup;
  acessorioForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private service: NotebookService,
    private router: Router
  ) {
    this.notebookForm = this.fb.group({
      nome: ['', Validators.required],
      descricao: ['', Validators.required],
      preco: [0, [Validators.required, Validators.min(1)]],
    });

    this.acessorioForm = this.fb.group({
      nome: ['', Validators.required],
      descricao: ['', Validators.required],
      preco: [0, [Validators.required, Validators.min(1)]],
    });
  }

  adicionarAcessorio(): void {
    this.mostrarCadastroAcessorio = true;
  }

  salvarAcessorio(): void {
    if (this.acessorioForm.invalid) {
      return;
    }

    const novoAcessorio: Acessorio = {
      ...this.acessorioForm.value,
    };

    this.acessorios = [...this.acessorios, novoAcessorio];

    this.acessorioForm.reset({
      nome: '',
      descricao: '',
      preco: 0,
    });

    this.mostrarCadastroAcessorio = false;
  }

  removerAcessorio(index: number): void {
    this.acessorios = this.acessorios.filter((_, i) => i !== index);
  }
  salvarNotebook(): void {
    if (this.notebookForm.invalid) {
      return;
    }

    const notebook: Notebook = {
      nome: this.notebookForm.value.nome,
      descricao: this.notebookForm.value.descricao,
      preco: Number(this.notebookForm.value.preco),
      acessorios: this.acessorios.map((acessorio) => ({
        nome: acessorio.nome,
        descricao: acessorio.descricao,
        preco: Number(acessorio.preco),
      })),
    };

    console.log('Payload enviado:', notebook);

    this.service.cadastrar(notebook).subscribe({
      next: (response) => {
        console.log('Notebook cadastrado:', response);

        this.fechar();
      },

      error: (erro) => {
        console.error('Erro ao cadastrar notebook:', erro);
      },
    });
  }

  limparFormulario(): void {
    this.notebookForm.reset({
      nome: '',
      descricao: '',
      preco: 0,
    });

    this.acessorios = [];

    this.acessorioForm.reset({
      nome: '',
      descricao: '',
      preco: 0,
    });

    this.mostrarCadastroAcessorio = false;
  }

  fechar(): void {
    this.router.navigate(['/notebooks']);
  }
}
