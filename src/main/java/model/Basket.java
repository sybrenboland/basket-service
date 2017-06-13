package model;

import java.util.List;

public class Basket {

    private String id;
    private List<Product> productList;

    public Basket() {}

    public Basket(String id, List<Product> productList) {
        this.id = id;
        this.productList = productList;
    }

    public String getId() {
        return id;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
