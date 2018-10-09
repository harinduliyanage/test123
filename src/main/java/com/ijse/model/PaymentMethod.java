/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
public class PaymentMethod implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String paymentMethod;
    
    @Transient
    @OneToMany(mappedBy = "paymentMethod")
    private List<Payment> payment;

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
     * @return the paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * @param paymentMethod the paymentMethod to set
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * @return the payment
     */
    public List<Payment> getPayment() {
        return payment;
    }

    /**
     * @param payment the payment to set
     */
    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }
    
}
