package com.ijse.dao;

import com.ijse.dao.custom.CategoryDaoCustom;
import com.ijse.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface CategoryDao extends JpaRepository<Category,Integer>, CategoryDaoCustom {
}