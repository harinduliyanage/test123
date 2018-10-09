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
public class ShoppingCartDetailCompositeId  implements Serializable{
    private ShoppingCart shoppingCart;
    private ItemDetails itemDetals;
    
    public ShoppingCartDetailCompositeId(){
        
    }

    /**
     * @return the shoppingCart
     */
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    /**
     * @param shoppingCart the size to set
     */
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    /**
     * @return the itemDetails
     */
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    public ItemDetails getItemDetails() {
        return itemDetals;
    }

    /**
     * @param itemDetails the item to set
     */
    public void setItemDetails(ItemDetails itemDetails) {
        this.itemDetals = itemDetails;
    }
}
