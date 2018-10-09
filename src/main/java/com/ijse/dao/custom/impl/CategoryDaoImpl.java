package com.ijse.dao.custom.impl;

import com.ijse.dao.custom.CategoryDaoCustom;
import com.ijse.dto.CategoryDTO;
import com.ijse.model.Category;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Transactional
@Repository
public class CategoryDaoImpl implements CategoryDaoCustom {

    private SessionFactory sessionFactory;

    @Autowired
    public CategoryDaoImpl(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    @Override
    public Category getCategoryByName(String name) throws Exception {
        Session s= sessionFactory.openSession();
        Criteria c2 =s.createCriteria(Category.class);
        c2.add(Restrictions.eq("name", name));
        c2.setMaxResults(1);
        Category category = (Category) c2.uniqueResult();
        s.close();
        return category;
    }

    @Override
    public List<Category> getPaginatedList(Integer offset, Integer limit) throws Exception {
        Session s= sessionFactory.openSession();
        Criteria criteria = s.createCriteria(Category.class);
        criteria.setFirstResult(offset);
        criteria.setMaxResults(limit);
        List<Category> page = criteria.list();
        s.close();
        return page;
    }

    @Override
    public boolean updateCategory(Category category) throws Exception {
        Session s= sessionFactory.openSession();
        s.update(category);
        s.close();
        return true;
    }
}
