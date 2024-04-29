import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProdutosService {

  private readonly API = 'http://localhost:8010/api/produtos';

  constructor(private httpClient: HttpClient) { }


  getAll(): Observable<Object[]> {
    return this.httpClient.get<Object[]>(this.API);
  }

  getById(id: number):Observable<Object> {
    return this.httpClient.get<Object>(`${this.API}/${id}`);
  }

}
