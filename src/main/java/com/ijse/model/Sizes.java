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
public class Sizes implements Serializable{ 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String sizes;//Xs,S,M,L,XL
    private String sizeUK;
    private String sizeUS;
    private String sizeEU;
    
    @Transient
    @OneToMany(mappedBy = "sizes",fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    Set<ItemDetails> itemDetails=new HashSet<ItemDetails>();

    public Sizes() {
    }
    
    public void setItemDetails(Set<ItemDetails> itemDetails){
        this.itemDetails=itemDetails;
    }
    
    public Set<ItemDetails> getItemDetails(){
        return this.itemDetails;
    }

    public String getSizeUK() {
        return sizeUK;
    }

    public void setSizeUK(String sizeUK) {
        this.sizeUK = sizeUK;
    }

    public String getSizeUS() {
        return sizeUS;
    }

    public void setSizeUS(String sizeUS) {
        this.sizeUS = sizeUS;
    }

    public String getSizeEU() {
        return sizeEU;
    }

    public void setSizeEU(String sizeEU) {
        this.sizeEU = sizeEU;
    }

    
    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public String getSizes() {
        return sizes;
    }

    
    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }
  
}
