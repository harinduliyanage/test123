package com.ijse.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ijse.dto.CategoryDTO;
import com.ijse.service.custom.CategoryService;
import com.ijse.util.JsonService;
import com.ijse.util.ResponseMassage;
import com.ijse.util.ResponseWrapper;
import com.ijse.util.StringConstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
public class CategoryController {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CategoryService categoryService;

    //Use for creating resources.
    @PostMapping(value = "/categories",consumes = "application/json")
    public ResponseEntity<JsonNode> addCategory(@RequestBody String payload){
        JsonNode jsonNode;
        CategoryDTO categoryDTO;
        try {
            //convert payload string to json
            jsonNode = objectMapper.readTree(payload);
            //convert json to POJO
            categoryDTO = objectMapper.treeToValue(jsonNode, CategoryDTO.class);

            //check category have a category name if not bad request
            if(StringConstance.isBlank(categoryDTO.getName())){
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.CATEGORY_MISSING_DATA, null))
                        );
            }

        }catch (IOException e){// if payload hasn't valid json
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(JsonService.toJsonNode(
                            new ResponseWrapper<>(ResponseMassage.INVALID_DATA, null))
                    );
        }

        //Everything's fine then save category POJO
        try {
            CategoryDTO add = categoryService.add(categoryDTO);
            if (add == null){
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.CATEGORY_NOT_ADDED,null)));
            }else {
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.SUCCESSFUL,add)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(JsonService.toJsonNode(
                            new ResponseWrapper<>(e.getMessage(),null)));
        }
    }

    //Use for retrieving a single resources.
    @GetMapping(value = "/categories/{id}")
    public ResponseEntity<JsonNode> getCategory(@PathVariable Integer id){
        try {
            //Find Category via ID
            CategoryDTO categoryDTO = categoryService.findById(id);
            //If category not found
            if (categoryDTO == null){
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.CATEGORY_NOT_FOUND,null)));
            }else {//If category Found
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.SUCCESSFUL,categoryDTO)
                        ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(JsonService.toJsonNode(
                            new ResponseWrapper<>(ResponseMassage.ERROR_WHILE_PROCESSING,null)
                    ));
        }
    }

    /*
        Use for retrieving a list of resources.
        @Param offset is starting index of page
        @Param limit is last index of page
     */
    @GetMapping(value = "/categories",params = {"offset","limit"})
    public ResponseEntity<JsonNode> getCategories(@RequestParam ("offset")Integer offset,
                                                  @RequestParam ("limit")Integer limit){

        try {
            List<CategoryDTO> paginatedList = categoryService.getPaginatedList(offset, limit);
            if (paginatedList.size() == 0){
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(JsonService.toJsonNode(new ResponseWrapper<>(
                                ResponseMassage.CATEGORIES_NOT_FOUND,null)));
            }else{
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(JsonService.toJsonNode(new ResponseWrapper<>(
                                ResponseMassage.SUCCESSFUL,paginatedList)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(JsonService.toJsonNode(new ResponseWrapper<>(e.getMessage(),null)));
        }
    }

    //Use for deleting resources.
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<JsonNode> deleteCategory(@PathVariable("id")Integer id){
        try {
            CategoryDTO byId = categoryService.findById(id);
            if (byId!=null){
                boolean deleted = categoryService.delete(byId);
                if (deleted){
                    return ResponseEntity
                            .status(HttpStatus.OK)
                            .body(JsonService.toJsonNode(
                                    new ResponseWrapper<>(ResponseMassage.CATEGORY_DELETED,null)
                            ));
                }else {
                    return ResponseEntity
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(JsonService.toJsonNode(
                                    new ResponseWrapper<>(ResponseMassage.ERROR_WHILE_PROCESSING,null)
                            ));
                }
            }else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.CATEGORY_NOT_FOUND,null)
                        ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(JsonService.toJsonNode(
                            new ResponseWrapper<>(e.getMessage(),null)
                    ));
        }

    }

    @GetMapping(value = "/categories/search",params = "name")
    public ResponseEntity<JsonNode> searchCategory(@RequestParam("name") String name){
        try {
            CategoryDTO  categoryByName = categoryService.getCategoryByName(name);
            if (categoryByName != null){
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.SUCCESSFUL,categoryByName)
                        ));
            }else{
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.CATEGORY_NOT_FOUND,null)
                        ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(JsonService.toJsonNode(
                            new ResponseWrapper<>(e.getMessage(),null)
                    ));
        }

    }
    //Use for replacing the entire resources or collections.
    @PutMapping(value = "/categories/{id}",consumes = "application/json")
    public ResponseEntity<JsonNode> updateCategory(@PathVariable Integer id,
                                                   @RequestBody String payload){
        try {
            CategoryDTO byId = categoryService.findById(id);
            //check resource exits
            if (byId!=null){
                try {
                    JsonNode jsonNode = JsonService.toJsonNode(payload);
                    CategoryDTO categoryDTO = objectMapper.treeToValue(jsonNode, CategoryDTO.class);
                    //check properties not null
                    if (!StringConstance.isBlank(categoryDTO.getName())) {
                        System.out.println(categoryDTO.getName());
                        byId.setName(categoryDTO.getName());
                        categoryService.update(byId);
                        return ResponseEntity
                                .status(HttpStatus.OK)
                                .body(JsonService.toJsonNode(
                                        new ResponseWrapper<>(ResponseMassage.SUCCESSFUL,byId)
                                ));
                    }
                    return ResponseEntity
                            .status(HttpStatus.BAD_REQUEST)
                            .body(JsonService.toJsonNode(
                                    new ResponseWrapper<>(ResponseMassage.CATEGORY_MISSING_DATA,null)
                            ));

                }catch (Exception e){
                    return ResponseEntity
                            .status(HttpStatus.BAD_REQUEST)
                            .body(JsonService.toJsonNode(
                                    new ResponseWrapper<>(e.getMessage(),null)
                            ));
                }

            }else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(JsonService.toJsonNode(
                                new ResponseWrapper<>(ResponseMassage.CATEGORY_NOT_FOUND,null)
                        ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(JsonService.toJsonNode(
                            new ResponseWrapper<>(e.getMessage(),null)
                    ));
        }
    }

}
