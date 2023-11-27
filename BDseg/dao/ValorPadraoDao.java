package dao;

import controller.ConnectionFactory;
import model.ValorPadrao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ValorPadraoDao {

    private final Connection con;

    public ValorPadraoDao() throws SQLException {
        this.con = ConnectionFactory.getConnection();
    }

    public ValorPadrao obterValoresPadroesPorId(int id) {
        String sql = "SELECT * FROM valorespadroes WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            ValorPadrao valoresPadroes = new ValorPadrao();

            if (rs.next()) {
                valoresPadroes.setId(rs.getInt("id"));
                valoresPadroes.setLimite(rs.getDouble("limite"));
                valoresPadroes.setUnidade(rs.getString("unidade"));
                valoresPadroes.setReferencia(rs.getDouble("referencia"));
            }

            stmt.close();
            rs.close();
            con.close();

            return valoresPadroes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<ValorPadrao> obterTodosValoresPadroes() {
        String sql = "SELECT * FROM valorespadroes";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<ValorPadrao> vps = new ArrayList<>();
            while (rs.next()) {
                ValorPadrao valorPadrao = new ValorPadrao();
                valorPadrao.setId(rs.getInt("id"));
                valorPadrao.setLimite(rs.getDouble("limite"));
                valorPadrao.setUnidade(rs.getString("unidade"));
                valorPadrao.setReferencia(rs.getInt("referencia"));
                
                vps.add(valorPadrao);
            }
            rs.close();
            stmt.close();
            con.close();
            return vps;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
