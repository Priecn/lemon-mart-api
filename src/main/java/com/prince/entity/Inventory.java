package com.prince.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Inventory implements Serializable {

    private static final long serialVersionUID = -54655123254645L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_seq")
    @SequenceGenerator(name = "inventory_seq", sequenceName = "inventory_seq", allocationSize = 1)
    private Long id;

    @OneToMany(mappedBy = "inventory", fetch = FetchType.EAGER)
    private List<Product> products;
    private Integer quantity;
    private Integer currentAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Integer currentAmount) {
        this.currentAmount = currentAmount;
    }
}
