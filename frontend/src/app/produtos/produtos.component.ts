import { Component, OnInit } from "@angular/core";
import { ProdutosService } from "../services/produtos.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-produtos',
  templateUrl: "produtos.component.html",
  styleUrl: "produtos.component.scss"
})
export class ProdutosComponent implements OnInit {

  produtos: any[] = [];
  produtosFiltrados: any[] = [];
  precoMinimo: number;
  precoMaximo: number;
  quantidadeMinima: number;
  nome: string = '';

  constructor(private produtosService: ProdutosService, private router: Router) { }

  ngOnInit(): void {
    this.getProducts();
  }

  private getProducts() {
    this.produtosService.getAll().subscribe(response => {
      this.produtos = response;
      this.filtrar();
    });
  }

  private filtrar() {
    this.produtosFiltrados = this.produtos
    .filter(produto => {
      const precoMinimoInRange = !this.precoMinimo || produto.preco >= this.precoMinimo;
      const precoMaximoInRange = !this.precoMaximo || produto.preco <= this.precoMaximo;
      const quantidadesInRange = !this.quantidadeMinima || produto.quantidades >= this.quantidadeMinima;
      const nomeMatch = !this.nome || produto.nome.toLowerCase().includes(this.nome.toLowerCase());
      return precoMinimoInRange && precoMaximoInRange && quantidadesInRange && nomeMatch;
    }
  )
  }

  atualizarFiltro() {
    this.filtrar();
  }


  getDetalhe(id: number) {
    this.produtosService.getById(id).subscribe({
      next: (response: Object) => {
        this.router.navigate(['/produtos', id]);
      },
      error: (error: any) => {
        console.error('Erro ao carregar detalhes do produto:', error);
      }
    });

  }
}
