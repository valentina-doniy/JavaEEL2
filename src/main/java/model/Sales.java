package model;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

public class Sales {
    private int salesId;
    private int productId;
    private int sellerId;
    private int countOfProducts;

    public Sales() {}

    public Sales(int salesId, int productId, int sellerId, int countOfProducts) {
        this.salesId = salesId;
        this.productId = productId;
        this.sellerId = sellerId;
        this.countOfProducts = countOfProducts;
    }

    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getCountOfProducts() {
        return countOfProducts;
    }

    public void setCountOfProducts(int countOfProducts) {
        this.countOfProducts = countOfProducts;
    }
}

