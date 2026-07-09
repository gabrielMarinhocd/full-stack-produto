export interface Notebook {
  id?: number;
  nome: string;
  descricao: string;
  preco: number;
  acessorios: Acessorio[];
}

export interface Acessorio {
  codigo?: number;
  nome: string;
  descricao: string;
  preco: number;
}
