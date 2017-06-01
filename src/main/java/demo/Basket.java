package demo;

import java.util.List;

public class Basket {

    private long id;
    private List<Product> productList;

    public Basket(long id, List<Product> productList) {
        this.id = id;
        this.productList = productList;
    }

    public long getId() {
        return id;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
