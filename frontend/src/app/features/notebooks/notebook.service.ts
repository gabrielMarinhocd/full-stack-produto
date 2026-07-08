import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { Notebook } from './models/notebook.model';

@Injectable({
  providedIn: 'root',
})
export class NotebookService {
  private readonly api = 'http://localhost:8080/notebooks';

  constructor(private http: HttpClient) {}

  listar(): Observable<Notebook[]> {
    return this.http.get<Notebook[]>(this.api);
  }

  listarComAcessorios(): Observable<Notebook[]> {
    return this.http.get<Notebook[]>(`${this.api}/acessorios`);
  }

  buscarPorId(id: number): Observable<Notebook> {
    return this.http.get<Notebook>(`${this.api}/${id}`);
  }

  buscarPorAcessorio(nome: string): Observable<Notebook[]> {
    return this.http.get<Notebook[]>(`${this.api}/acessorios/buscar`, {
      params: {
        nome,
      },
    });
  }

  cadastrar(notebook: Notebook): Observable<Notebook> {
    return this.http.post<Notebook>(this.api, notebook);
  }

  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}
