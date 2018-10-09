/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "SHOPPINGCART_DETAILS")
public class ShoppingCartDetails implements Serializable{
    private int id;
    private ShoppingCart shoppingCart;
    private ItemDetails itemDetails;
    
    private int orderQty;
    private double amount;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CART_DETAILS_ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @ManyToOne(cascade = CascadeType.PERSIST,optional = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "SHOPPINGCART_ID",nullable=false)
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "ITEM_DETAILS_ID")
    public ItemDetails getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ItemDetails itemDetails) {
        this.itemDetails = itemDetails;
    }
    
    /**
     * @return the orderQty
     */
    public int getOrderQty() {
        return orderQty;
    }

    /**
     * @param orderQty the orderQty to set
     */
    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
}
