import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { ProdutosService } from '../../services/produtos.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-produto-detalhe',
  templateUrl: './produto-detalhe.component.html',
  styleUrls: ['./produto-detalhe.component.scss']
})
export class ProdutoDetalheComponent implements OnInit {

  produto: any;
  mostrarModalVenda = false;

  @Output() produtoSelecionado = new EventEmitter<number>();

  constructor(private produtosService: ProdutosService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.getDetalhe();
  }

  getDetalhe() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.produtosService.getById(id).subscribe({
      next: (produto: any) => {
        this.produto = produto;
      },
      error: (error: any) => {
        console.error('Erro ao carregar detalhes do produto:', error);
      }
    })
  }

  abrirModalVenda() {
    this.mostrarModalVenda = true;
    this.produtoSelecionado.emit(this.produto.id);
  }



  fecharModalVenda(): void {
    this.mostrarModalVenda = false;
  }

}
