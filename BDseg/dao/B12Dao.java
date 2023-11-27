package dao;

import controller.ConnectionFactory;
import model.B12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class B12Dao {

    private Connection con;

    public B12Dao() throws SQLException {
        this.con = ConnectionFactory.getConnection();
    }

    public void adicionarB12(B12 b12) {
        String sql = "insert into b12 (resultado) values (?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, b12.getResultado());
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public B12 obterB12PorId(Integer id) {
        String sql = "SELECT * FROM b12 WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            B12 b12 = new B12();
            while (rs.next()) {
                b12.setId(rs.getInt("id"));
                b12.setResultado(rs.getString("resultado"));

            }
            stmt.close();
            rs.close();
            con.close();
            return b12;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<B12> obterTodasB12s() {
        String sql = "SELECT * FROM b12";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<B12> b12s = new ArrayList<>();
            while (rs.next()) {
                B12 b12 = new B12();
                b12.setId(rs.getInt("id"));
                b12.setResultado(rs.getString("resultado"));
                b12s.add(b12);
            }
            rs.close();
            stmt.close();
            con.close();
            return b12s;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void atualizarB12(B12 b12, Integer id) {
        String sql = "update b12 set resultado = ?  where id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, b12.getResultado());
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void deletarB12(int id) throws SQLException {
        String sql = "DELETE FROM b12 WHERE id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
