package model.dao;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
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

    public ProductDAO() {

    }

    public List<Product> select() throws SQLException {
        String sql = "SELECT * FROM product ORDER BY id";
        PreparedStatement ps = connection.prepareStatement(sql);
        List<Product> productList = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            productList.add(new Product(rs.getInt("id"), rs.getString("product_name"), rs.getInt("price")));
        }
        ps.close();
        rs.close();
        return productList;
    }

    public void delete(int id) throws SQLException{
        String sql = "DELETE FROM product WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }

    public void update(Product product) throws SQLException{
        String sql = "UPDATE product SET product_name = ?, price = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, product.getProductName());
        ps.setInt(2, product.getPrice());
        ps.setInt(3, product.getProductId());
        ps.executeUpdate();
        ps.close();
    }

    public void add(Product product) throws SQLException {
        String sql = "INSERT INTO product (id, product_name, price) VALUES ((SELECT max(id)+1 FROM product), ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, product.getProductName());
        ps.setInt(2, product.getPrice());
        ps.executeUpdate();
        ps.close();
    }

    public List<Integer> selectIds() throws SQLException {
        String sql = "SELECT id FROM product ORDER BY id";
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
