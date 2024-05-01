import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Sales } from '../models/sales';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VendasService {


  private readonly API = 'http://localhost:8010/api/vendas';

  constructor(private httpClient: HttpClient) {}

  inserirVenda(sales: Sales): Observable<object> {
    return this.httpClient.post(`${this.API}`, sales);
  }
}
