package com.prince.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = -5454512154554512L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
    private String name;
    private Integer sku;
    private Integer upc;
    private Float price;
    private Float discountModifier;
    private Boolean listingStatus;

    @ManyToOne
    @JoinColumn(name = "INVENTORY_ID")
    private Inventory inventory;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private List<Receipt> receipts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
    }

    public Integer getUpc() {
        return upc;
    }

    public void setUpc(Integer upc) {
        this.upc = upc;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getDiscountModifier() {
        return discountModifier;
    }

    public void setDiscountModifier(Float discountModifier) {
        this.discountModifier = discountModifier;
    }

    public Boolean getListingStatus() {
        return listingStatus;
    }

    public void setListingStatus(Boolean listingStatus) {
        this.listingStatus = listingStatus;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
