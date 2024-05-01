import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ProdutoDetalheComponent } from './produto-detalhe.component';
import { VendasModule } from '../../vendas/vendas.module';


const routes: Routes = [
  {path: '', component: ProdutoDetalheComponent}
]


@NgModule({
  declarations: [ProdutoDetalheComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    VendasModule
  ]
})
export class ProdutoDetalheModule { }
