package com.example.demo.webservices;

import com.example.demo.interfaces.Produtos;
import com.example.demo.interfaces.Vendas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/vendas")
public class VendasWs {

    @Autowired
    private Vendas vendas;

    @Autowired
    private Produtos produtos;


    @PostMapping()
    public ResponseEntity<Map<String, Object>> createSale(@RequestBody Map<String, Object> sale) {
        try {
            vendas.insertSale(sale);

            int produtoId = (int) sale.get("produtoId");
            int quantidadeVendida = (int) sale.get("quantidades");
            produtos.updateQuantity(produtoId, quantidadeVendida);

            Map<String, Object> response = new HashMap<>();

            return ResponseEntity.ok().body(response);
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar venda: " + e.getMessage());
            Map<String, Object> response = new HashMap<>();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}