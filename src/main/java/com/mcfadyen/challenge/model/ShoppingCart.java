package com.mcfadyen.challenge.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

public class ShoppingCart {
    private ArrayList<CommerceItem> commerceItems;


    public ShoppingCart() {
        this.commerceItems = new ArrayList<>();
    }

    public ArrayList<CommerceItem> getCommerceItems() {
        return commerceItems;
    }

    public void setCommerceItems(ArrayList<CommerceItem> commerceItems) {
        this.commerceItems = commerceItems;
    }

    /**
     * Push the commerceItem into array
     *
     * @param commerceItem
     * @return
     */
    public CommerceItem addCommerceItem(CommerceItem commerceItem) {

        CommerceItem found = null;
        CommerceItem current;

        for (int i = 0; i < this.commerceItems.size(); i++) {
            if (commerceItem.getProduct().getId().equals(this.commerceItems.get(i).getProduct().getId())) {
                found = this.commerceItems.get(i);
                found.setQuantity(found.getQuantity() + commerceItem.getQuantity());
            }
        }

        if (found == null) {
            commerceItem.setId(this.commerceItems.size());
            current = commerceItem;
            this.commerceItems.add(current);
        } else {
            current = found;
        }

        return current;
    }

    public CommerceItem removeCommerceItemById (int id) throws Exception {
        CommerceItem commerceItemFound = null;
        for (int i = 0; i < this.commerceItems.size(); i++) {
             CommerceItem commerceItem = this.commerceItems.get(i);
            if (commerceItem.getId() == id) {
                commerceItemFound = commerceItem;
                this.commerceItems.remove(commerceItem);
            }
        }

        if(commerceItemFound == null) {
            throw new Exception("Commerce item did not found");
        }

        return commerceItemFound;
    }

    /**
     * Calculates the total amount
     *
     * @return
     */
    public BigDecimal getAmount() {
        BigDecimal amount = new BigDecimal(0);
        for (int i = 0; i < this.commerceItems.size(); i++) {
            amount = amount.add(this.commerceItems.get(i).getProduct().getPrice().multiply(new BigDecimal(this.commerceItems.get(i).getQuantity())));
        }
        return amount;
    }


    @Override
    public int hashCode() {
        return Objects.hash(Math.random());
    }

}
