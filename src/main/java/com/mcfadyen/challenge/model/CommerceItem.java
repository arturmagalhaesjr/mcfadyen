package com.mcfadyen.challenge.model;

import java.math.BigDecimal;

public class CommerceItem {
    private Integer id;
    private Product product;
    private Integer quantity;

    public CommerceItem () {
        quantity = 1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Retrieves the amount of CommerceItem
     * @return
     */
    public BigDecimal getAmount () {
        return this.product.getPrice().multiply(new BigDecimal(this.quantity));
    }
}
