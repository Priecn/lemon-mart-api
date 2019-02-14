package com.prince.entity;

import com.prince.security.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Receipt implements Serializable {

    private static final long serialVersionUID = -5453421217454231L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receipt_seq")
    @SequenceGenerator(name = "receipt_seq", sequenceName = "receipt_seq", allocationSize = 1)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "RECEIPT_PRODUCT",
            joinColumns = {@JoinColumn(name = "RECEIPT_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")}
    )
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "CASHIER_ID")
    private User cashier;

    @ManyToOne
    @JoinColumn(name = "BUYER_ID")
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "MANAGER_ID")
    private User manager;

    private Double subTotal;
    private Float tax;
    private Double total;
    private String paymentType;
    private Double paymentAmount;

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

    public User getCashier() {
        return cashier;
    }

    public void setCashier(User cashier) {
        this.cashier = cashier;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Float getTax() {
        return tax;
    }

    public void setTax(Float tax) {
        this.tax = tax;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
