package com.example.demo.interfaces;

import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.Map;

public interface Vendas {

    public ResponseEntity<?> insertSale(Map<String, Object> sale) throws SQLException;

}
