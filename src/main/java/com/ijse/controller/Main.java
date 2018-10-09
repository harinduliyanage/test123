package com.ijse.controller;

import com.ijse.dao.CategoryDao;
import com.ijse.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Main {
    @Autowired
    CategoryDao categoryDao;

    @GetMapping("/")
    @ResponseBody
    public String defaultPath(){
        return "Hello";
    }

}
