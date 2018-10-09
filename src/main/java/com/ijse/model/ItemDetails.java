/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "ITEM_DETAILS")
public class ItemDetails implements Serializable{
    
    private Integer id;
    private Sizes sizes;
    private Item item;
    //additonal feelds
    private double unitPrice;
    private int qtyOnHand;
    
    private Set<ShoppingCartDetails> shoppingCartDetails = new HashSet<ShoppingCartDetails>();
    
    public ItemDetails(){    
    }
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ITEMDETAILS_ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public double getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
   
    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "SIZES_ID")  
    public Sizes getSizes() {
        return sizes;
    }

    public void setSizes(Sizes sizes) {
        this.sizes = sizes;
    }

    @ManyToOne(cascade = CascadeType.PERSIST,optional = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "ITEM_ID")  
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
}
