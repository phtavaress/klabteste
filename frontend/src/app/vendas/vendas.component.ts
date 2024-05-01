import { Component, EventEmitter, Inject, Input, Output } from '@angular/core';
import { VendasService } from '../services/vendas.service';
import { Sales } from '../models/sales';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-vendas',
  templateUrl: './vendas.component.html',
  styleUrl: './vendas.component.scss'
})
export class VendasComponent {

  @Input() produto: any;
  @Output() fecharModal = new EventEmitter<void>();

  constructor(private vendasService: VendasService) {}

  nome: string = '';
  quantidade: number = 0;




  cadastrarVenda() {
    const totalVenda = this.produto.preco * this.quantidade;
    const venda = new Sales(this.nome, this.produto.id, this.quantidade,  totalVenda);

    this.vendasService.inserirVenda(venda).subscribe(resp => {
      console.log('resp', resp);
      console.log(venda)

    }, error => {
      console.error('Erro ao cadastrar venda:', error);
    });
  }

  closeModal() {
    this.fecharModal.emit();
    this.nome = '';
    this.quantidade = 0;

  }
}
