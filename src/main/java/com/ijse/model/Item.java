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
@Table(name = "Item")
public class Item implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String description;
    private String paths;
    
    @Transient
    @OneToMany(mappedBy = "item",fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)      
    Set<ItemDetails> itemDetails=new HashSet<ItemDetails>();
    
    public Item(){
        
    }
    
    @ManyToOne(optional = true,fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name="Category_id",nullable=false)
    private Category category;

    public void setItemDetails(Set<ItemDetails> itemDetails){
        this.itemDetails=itemDetails;
    }
    
    public Set<ItemDetails> getItemDetails(){
        return this.itemDetails;
    }
    /**
     * @return the itemCode
     */
    public Integer getItemCode() {
        return id;
    }

    /**
     * @param itemCode the itemCode to set
     */
    public void setItemCode(Integer itemCode) {
        this.id = itemCode;
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

    /**
     * @return the path
     */
    public String getPaths() {
        return paths;
    }

    /**
     * @param path the path to set
     */
    public void setPaths(String path) {
        this.paths = path;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }
    
    
}
