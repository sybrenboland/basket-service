package org.shboland.model.basket;

import org.shboland.model.product.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "basket")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @JoinColumn(name = "BASKET_ID", nullable = false)
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Product.class, orphanRemoval = true)
    private List<Product> productList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
