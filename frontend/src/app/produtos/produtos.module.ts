import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {Route, Router, RouterModule, Routes} from "@angular/router";
import {ProdutosComponent} from "./produtos.component";
import { HttpClientModule } from "@angular/common/http";

const route = [{component: ProdutosComponent, path: ''}]
@NgModule({
  imports: [CommonModule, RouterModule.forChild(route), HttpClientModule],
  declarations: [ProdutosComponent],
  exports: [ProdutosComponent]
})
export class ProdutosModule {}
