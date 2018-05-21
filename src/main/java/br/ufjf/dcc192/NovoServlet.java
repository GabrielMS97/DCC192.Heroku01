/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc192;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ice
 */
@WebServlet(name = "NovoServlet", urlPatterns = {"/index.html"})
public class NovoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher despachante = req.getRequestDispatcher("/WEB-INF/index.jsp");
        Connection conexao;
        List<String> nomes = new ArrayList<String>();
        try {
            conexao = Conexao.getConnection();
            criaTabelaSeNaoExiste(conexao);
            insereRegistroAleatorio(conexao);
            nomes = listaNomes(conexao);
            conexao.close();
        } catch (Exception e) {
            System.err.println("ERRO" + e);
        }

        req.setAttribute("mensagem", "Olá mundo!");
        req.setAttribute("nomes", nomes);
        despachante.forward(req, resp);
    }

    private void criaTabelaSeNaoExiste(Connection conexao) throws SQLException {
        Statement operacao = conexao.createStatement();
        operacao.executeUpdate("CREATE TABLE IF NOT EXISTS pessoas(nome VARCHAR(200))");
        operacao.close();
    }

    private void insereRegistroAleatorio(Connection conexao) throws SQLException {
        Statement comando = conexao.createStatement();
        Random rnd = new Random();
        Integer n = rnd.nextInt(1000);
        comando.executeUpdate(String.format("INSERT INTO pessoas(nome) VALUES('Pessoa %d')", n));
        comando.close();
    }

    private List<String> listaNomes(Connection conexao) throws SQLException {
        ArrayList<String> lista = new ArrayList<String>();
        Statement operacao = conexao.createStatement();
        ResultSet resultado = operacao.executeQuery("SELECT nome FROM pessoas");
        while (resultado.next()) {
            lista.add(resultado.getString("nome"));
        }
        return lista;
    }

}
