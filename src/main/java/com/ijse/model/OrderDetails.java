package com.ijse.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class OrderDetails {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Integer id;
     
     @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
     private Orders order;
     
     private int orderQty;
     private String description;
     private int cartDetailID;

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

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public int getCartDetailID() {
        return cartDetailID;
    }

    public void setCartDetailID(int cartDetailID) {
        this.cartDetailID = cartDetailID;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the order
     */
    public Orders getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Orders order) {
        this.order = order;
    }
    
     public Integer getOrderID() {
        return order.getId();
    }

    /**
     * @param order the order to set
     */
    public void setOrderID(Integer id) {
        this.order.setId(id);
    }
    
}
