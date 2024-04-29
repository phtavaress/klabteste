package com.example.demo.models;

import com.example.demo.interfaces.Produtos;
import com.example.demo.services.NativeScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Construa suas regras de negócio da forma que for necessária.
 * Se basear nos exemplos abaixo, complementando-os, ou até mesmo melhorando-os.
 * As operações no devem ser feitas por meio de strings SQL.
 */
@Service
public class ProdutoModel implements Produtos {

    @Autowired
    private NativeScriptService nativeScriptService;


    public Object getAllProducts() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            List<Map<String, Object>> listMap = new ArrayList<>();

            //Construção da string SQL
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM produtos;");

            //Abertura da conexão com o banco e abertura da PreparedStatement para comunicação
            connection = nativeScriptService.getConectionDb();
            preparedStatement = nativeScriptService.getPreparedStatementDb(sql.toString(), connection);

            //Conversão e retorno das informações
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Map<String,Object> map = new HashMap<>();
                map.put("id", rs.getObject("id"));
                map.put("nome", rs.getObject("nome"));
                map.put("preco", rs.getObject("preco"));
                map.put("quantidades", rs.getObject("quantidades"));
                map.put("defeitos", rs.getObject("defeitos"));

                listMap.add(map);
            }
            return listMap;
        } catch (SQLException e) {
            System.out.println("Erro ao consultar produtos: " + e.getMessage());
            e.printStackTrace();
            throw new SQLException("Erro ao consultar produtos no banco de dados.", e.getMessage());
        } finally {
            //Fechamento das conexões
            connection.close();
            preparedStatement.close();
        }
    }

    @Override
    public Object getById(Integer id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Map<String, Object> produto = new HashMap<>();

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM produtos WHERE id = ?;");

            connection = nativeScriptService.getConectionDb();
            preparedStatement = nativeScriptService.getPreparedStatementDb(sql.toString(), connection);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                produto.put("id", rs.getInt("id"));
                produto.put("nome", rs.getString("nome"));
                produto.put("preco", rs.getDouble("preco"));
                produto.put("quantidades", rs.getInt("quantidades"));
                produto.put("defeitos", rs.getInt("defeitos"));
                return produto;

            } else {
                throw new SQLException("Produto não encontrado para o ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar produto por ID: " + e.getMessage());
            throw e;

        } finally {
            connection.close();
            preparedStatement.close();
        }
    }

    public void insertProduct(Map<String, Object> product) throws SQLException {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO produtos (nome, preco) VALUES ('")
                    .append(product.get("name")).append("', ")
                    .append(product.get("preco")).append(")");

            System.out.println("SQL para inserir produto: " + sql);
            nativeScriptService.execute(sql.toString());
        } catch (Exception e) {
            System.out.println("Erro ao inserir produto: " + e.getMessage());
            e.printStackTrace();
            throw new SQLException("Erro ao inserir produto no banco de dados.", e.getMessage());
        }
    }
}
