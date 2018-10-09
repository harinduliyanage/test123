package com.ijse.dao.custom;

import com.ijse.model.Category;

import java.util.List;

public interface CategoryDaoCustom {

    public Category getCategoryByName(String name)throws Exception;
    public List<Category> getPaginatedList(Integer offset, Integer limit) throws Exception;
    public boolean updateCategory(Category category)throws Exception;
}
