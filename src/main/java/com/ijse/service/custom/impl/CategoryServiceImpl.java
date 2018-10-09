package com.ijse.service.custom.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ijse.dao.CategoryDao;
import com.ijse.dto.CategoryDTO;
import com.ijse.model.Category;
import com.ijse.service.custom.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public CategoryDTO add(CategoryDTO categoryDTO) throws Exception {
        Category category = convertToModel(categoryDTO);
        Category save = categoryDao.save(category);
        CategoryDTO savedDTO = convertToDTO(save);
        return savedDTO;
    }

    @Override
    public boolean update(CategoryDTO categoryDTO) throws Exception {
        Category category = convertToModel(categoryDTO);
        return categoryDao.updateCategory(category);
    }

    @Override
    public boolean delete(CategoryDTO categoryDTO) throws Exception {
        Category category = convertToModel(categoryDTO);
        categoryDao.delete(category);
        return true;
    }

    @Override
    public CategoryDTO findById(Integer id) throws Exception {
        Optional<Category> byId = categoryDao.findById(id);
        boolean present = byId.isPresent();
        if (present){
            CategoryDTO categoryDTO = convertToDTO(byId.get());
            return categoryDTO;
        }else {
            return null;
        }
    }

    @Override
    public Iterable<CategoryDTO> getAll() throws Exception {
        return null;
    }

    @Override
    public CategoryDTO getCategoryByName(String name) throws Exception {
        Category categoryByName = categoryDao.getCategoryByName(name);
        if (categoryByName!=null){
            CategoryDTO categoryDTO = convertToDTO(categoryByName);
            return categoryDTO;
        }else {
            return null;
        }
    }

    @Override
    public List<CategoryDTO> getPaginatedList(Integer offset, Integer limit) throws Exception {
        List<Category> paginatedList = categoryDao.getPaginatedList(offset, limit);
        List<CategoryDTO> dtoList = convertToDTOList(paginatedList);
        return dtoList;
    }

    //Todo: Make below containt Single Response Class for re-userbility
    protected Category convertToModel(CategoryDTO categoryDTO)throws Exception{
        Category category = objectMapper.convertValue(categoryDTO, Category.class);
        return category;
    }
    protected CategoryDTO convertToDTO(Category category)throws Exception{
        CategoryDTO categoryDTO = objectMapper.convertValue(category, CategoryDTO.class);
        return categoryDTO;
    }
    protected List<CategoryDTO> convertToDTOList(List<Category> categories)throws Exception{
        List<CategoryDTO> dtoList =objectMapper.convertValue(categories,new TypeReference<List<CategoryDTO>>(){});
        return dtoList;
    }
}
