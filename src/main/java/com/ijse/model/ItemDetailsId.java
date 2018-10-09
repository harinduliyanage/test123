/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.model;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Embeddable
class ItemDetailsId  implements Serializable{
    private Sizes size;
    private Item item;

    /**
     * @return the size
     */
    @ManyToOne(cascade = CascadeType.ALL)
    public Sizes getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Sizes size) {
        this.size = size;
    }

    /**
     * @return the item
     */
    @ManyToOne(cascade = CascadeType.ALL)
    public Item getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }
}
