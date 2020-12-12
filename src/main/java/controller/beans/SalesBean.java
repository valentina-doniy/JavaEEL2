package controller.beans;

import model.Sales;
import model.Seller;
import model.dao.SalesDAO;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Named(value = "salesBean")
public class SalesBean {
    private Sales sales;
    private SalesDAO salesDAO = new SalesDAO();

    public SalesBean() {
        sales = new Sales();
    }

    public Sales getSales() {
        return sales;
    }

    public List<Sales> getSalesList(){
        try {
            return salesDAO.select();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void deleteSales(int id){
        try {
            salesDAO.delete(id);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/index.xhtml");
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addSales(Sales sales){
        try {
            salesDAO.add(sales);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/index.xhtml");
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateSales(Sales sales){
        try {
            salesDAO.update(sales);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/index.xhtml");
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Integer> getSelectIds() {
        try {
            return salesDAO.selectIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }
}
