package com.ijse.util;

import com.ijse.dto.CategoryDTO;
import com.ijse.service.custom.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DefaultDataInsertModule {

    /*
    * Spring Boot will run ALL CommandLineRunner beans once the application context is loaded
    * @Param Inject Services
    */
    @Bean
    public CommandLineRunner initDatabase(CategoryService categoryService){
        return args -> {
            //Categories
            CategoryDTO c = new CategoryDTO();
            c.setName("Beach Wear");
            categoryService.add(c);
            CategoryDTO c1 = new CategoryDTO();
            c1.setName("Sport");
            categoryService.add(c1);
            //Item

            //Order , ect
        };
    }
}
