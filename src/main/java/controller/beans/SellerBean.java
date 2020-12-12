package controller.beans;

import model.Product;
import model.Seller;
import model.dao.SellerDAO;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Named(value = "sellerBean")
public class SellerBean {
    private Seller seller;
    private SellerDAO sellerDAO = new SellerDAO();

    public SellerBean() {
        seller = new Seller();
    }

    public Seller getSeller() {
        return seller;
    }

    public List<Seller> getSellers(){
        try {
            return sellerDAO.select();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void deleteSeller(int id){
        try {
            sellerDAO.delete(id);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/index.xhtml");
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addSeller(Seller seller){
        try {
            sellerDAO.add(seller);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/index.xhtml");
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateSeller(Seller seller){
        try {
            sellerDAO.update(seller);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/index.xhtml");
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Integer> getSelectIds() {
        try {
            return sellerDAO.selectIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }
}

