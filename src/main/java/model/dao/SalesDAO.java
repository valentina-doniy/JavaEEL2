package model.dao;

import model.Sales;
import model.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesDAO {
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

    public SalesDAO() {

    }

    public List<Sales> select() throws SQLException {
        String sql = "SELECT * FROM sales ORDER BY id";
        PreparedStatement ps = connection.prepareStatement(sql);
        List<Sales> sellerList = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            sellerList.add(new Sales(rs.getInt("id"), rs.getInt("product_id"), rs.getInt("seller_id"), rs.getInt("count_of_products")));
        }
        ps.close();
        rs.close();
        return sellerList;
    }

    public void delete(int id) throws SQLException{
        String sql = "DELETE FROM sales WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }

    public void update(Sales sales) throws SQLException{
        String sql = "UPDATE sales SET product_id = ?, seller_id = ?, count_of_products = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, sales.getProductId());
        ps.setInt(2, sales.getSellerId());
        ps.setInt(3, sales.getCountOfProducts());
        ps.executeUpdate();
        ps.close();
    }

    public void add (Sales sales) throws SQLException {
        String sql = "INSERT INTO sales (id, product_id, seller_id, count_of_products) VALUES ((SELECT max(id)+1 FROM sales), ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, sales.getProductId());
        ps.setInt(2, sales.getSellerId());
        ps.setInt(3, sales.getCountOfProducts());
        ps.executeUpdate();
        ps.close();
    }

    public List<Integer> selectIds() throws SQLException {
        String sql = "SELECT id FROM sales ORDER BY id";
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
