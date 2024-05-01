import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VendasComponent } from './vendas.component';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [VendasComponent],
  imports: [
    CommonModule,
    FormsModule
  ],
  exports: [VendasComponent]
})
export class VendasModule { }
