/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.model;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Embeddable
public class ShoppingCartId implements Serializable{
    private ItemDetails itemDetails;
    private ShoppingCart shoppingCart; 

    /**
     * @return the itemDetails
     */
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    public ItemDetails getItemDetails() {
        return itemDetails;
    }

    /**
     * @param itemDetails the itemDetails to set 
     */
    public void setItemDetails(ItemDetails itemDetails) {
        this.itemDetails = itemDetails;
    }

    /**
     * @return the shoppingCart
     */
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    /**
     * @param shoppingCart the shoppingCart to set
     */
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    
}
