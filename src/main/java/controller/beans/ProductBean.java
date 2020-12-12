package controller.beans;

import model.Product;
import model.dao.ProductDAO;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Named(value = "productBean")
public class ProductBean {
    private Product product;
    private ProductDAO productDAO = new ProductDAO();

    public ProductBean() {
        product = new Product();
    }

    public Product getProduct() {
        return product;
    }

    public List<Product> getProducts(){
        try {
            return productDAO.select();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void deleteProduct(int id){
        try {
            productDAO.delete(id);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/index.xhtml");
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addProduct(Product car){
        try {
            productDAO.add(car);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/index.xhtml");
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateProduct(Product car){
        try {
            productDAO.update(car);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/index.xhtml");
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Integer> getSelectIds() {
        try {
            return productDAO.selectIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }
}
