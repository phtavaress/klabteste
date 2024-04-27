import { Component, OnInit } from "@angular/core";
import { ProdutosService } from "./produtos.service";

@Component({
  selector: 'app-produtos',
  templateUrl: "produtos.component.html",
  styleUrl: "produtos.component.scss"
})
export class ProdutosComponent implements OnInit {

  produtos: any[] = [];

  constructor(private produtosService: ProdutosService) { }

  ngOnInit(): void {
    this.getProducts();
  }

  private getProducts() {
    this.produtosService.getAll().subscribe(response => {
      this.produtos = response;
    });
  }

}
