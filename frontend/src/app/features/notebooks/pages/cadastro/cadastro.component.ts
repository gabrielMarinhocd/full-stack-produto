import { Component } from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';

import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';

import { NotebookService } from '../../notebook.service';
import { Notebook } from '../../models/notebook.model';

@Component({
  selector: 'app-cadastro',
  standalone: true,
  imports: [
    ReactiveFormsModule,

    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatDividerModule,
  ],
  templateUrl: './cadastro.component.html',
  styleUrl: './cadastro.component.scss',
})
export class CadastroComponent {
  form: FormGroup;

  constructor(private fb: FormBuilder, private service: NotebookService) {
    this.form = this.fb.group({
      nome: ['', Validators.required],

      descricao: ['', Validators.required],

      preco: [0, [Validators.required, Validators.min(1)]],

      acessorios: this.fb.array([]),
    });
  }

  get acessorios(): FormArray {
    return this.form.get('acessorios') as FormArray;
  }

  novoAcessorio(): FormGroup {
    return this.fb.group({
      nome: ['', Validators.required],

      descricao: ['', Validators.required],

      preco: [0, [Validators.required, Validators.min(1)]],
    });
  }

  adicionarAcessorio() {
    this.acessorios.push(this.novoAcessorio());
  }

  removerAcessorio(index: number) {
    this.acessorios.removeAt(index);
  }

  salvar() {
    if (this.form.invalid) {
      return;
    }

    const notebook = this.form.value as Notebook;

    this.service.cadastrar(notebook).subscribe({
      next: () => {
        this.form.reset();

        this.acessorios.clear();
      },

      error: (erro) => {
        console.error('Erro ao cadastrar notebook', erro);
      },
    });
  }
}
