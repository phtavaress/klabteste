package com.example.demo.models;

import com.example.demo.interfaces.Vendas;
import com.example.demo.services.NativeScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class VendaModel implements Vendas {

    @Autowired
    private NativeScriptService nativeScriptService;

    @Override

    public ResponseEntity<?> insertSale(Map<String, Object> sale) {

        PreparedStatement preparedStatement = null;


        try {

            StringBuilder sql = new StringBuilder();


            sql.append("INSERT INTO vendas (produto_id, comprador, quantidades, total_venda) VALUES (?, ?, ?, ?);");


            Connection connection = nativeScriptService.getConectionDb();


            preparedStatement = nativeScriptService.getPreparedStatementDb(sql.toString(), connection);


            if (!sale.containsKey("produtoId")) {

                throw new NullPointerException("produto_id is missing");

            }


            preparedStatement.setInt(1, (int) sale.get("produtoId"));


            if (!sale.containsKey("comprador")) {

                throw new NullPointerException("comprador is missing");

            }


            preparedStatement.setString(2, (String) sale.get("comprador"));


            if (!sale.containsKey("quantidades")) {

                throw new NullPointerException("quantidades is missing");

            }


            preparedStatement.setInt(3, (int) sale.get("quantidades"));


            if (!sale.containsKey("total_venda")) {

                throw new NullPointerException("total_venda is missing");

            }


            Number totalVendaNumber = (Number) sale.get("total_venda");

            Double totalVendaDouble = totalVendaNumber.doubleValue();

            BigDecimal totalVenda = BigDecimal.valueOf(totalVendaDouble);

            preparedStatement.setBigDecimal(4, totalVenda);


            preparedStatement.executeUpdate();


            preparedStatement.close();


            connection.close();


            Map<String, Object> response = new HashMap<>();


            return ResponseEntity.ok().body(response);


        } catch (SQLException e) {

            Map<String, Object> response = new HashMap<>();


            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }

    }

}
