package model.dao;

import model.Product;
import model.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SellerDAO {
    private static Connection connection;

    static{
        String username = "postgres";
        String password = "123";
        String URL = "jdbc:postgresql://localhost:5432/accounting_sales";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public SellerDAO() {

    }

    public List<Seller> select() throws SQLException {
        String sql = "SELECT * FROM seller ORDER BY id";
        PreparedStatement ps = connection.prepareStatement(sql);
        List<Seller> sellerList = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            sellerList.add(new Seller(rs.getInt("id"), rs.getString("seller_name")));
        }
        ps.close();
        rs.close();
        return sellerList;
    }

    public void delete(int id) throws SQLException{
        String sql = "DELETE FROM seller WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }

    public void update(Seller seller) throws SQLException{
        String sql = "UPDATE seller SET seller_name = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, seller.getSellerName());
        ps.setInt(2, seller.getSellerId());
        ps.executeUpdate();
        ps.close();
    }

    public void add(Seller seller) throws SQLException {
        String sql = "INSERT INTO seller (id, seller_name) VALUES ((SELECT max(id)+1 FROM seller), ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, seller.getSellerName());
        ps.executeUpdate();
        ps.close();
    }

    public List<Integer> selectIds() throws SQLException {
        String sql = "SELECT id FROM seller ORDER BY id";
        PreparedStatement ps = connection.prepareStatement(sql);
        List<Integer> idList = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            idList.add(rs.getInt("id"));
        }
        ps.close();
        rs.close();
        return idList;
    }
}
