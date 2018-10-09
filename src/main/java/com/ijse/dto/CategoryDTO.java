package com.ijse.dto;

import com.ijse.model.Item;

import java.util.HashSet;
import java.util.Set;

public class CategoryDTO {
    private Integer categoryid;
    private String name;
    private Set<Item> items = new HashSet<>();


    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
