package com.ijse.service.custom;

import com.ijse.dto.CategoryDTO;
import com.ijse.service.SuperService;

import java.util.List;

public interface CategoryService extends SuperService<CategoryDTO> {
    public CategoryDTO getCategoryByName(String name)throws Exception;
    public List<CategoryDTO> getPaginatedList(Integer offset,Integer limit)throws Exception;
}
