package com.example.proyectobanca.controller;

import com.example.proyectobanca.model.Category;
import com.example.proyectobanca.model.User;
import com.example.proyectobanca.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Get all categories
     * @return List of categories from database
     */
    @GetMapping("/categories")
    @ApiOperation("Get all categories")
    public ResponseEntity<List<Category>> findAll(
            @ApiParam("Category name for search (optional): String") @QueryParam("name") String name,
            @ApiParam("Pagination: page from which the records start to be displayed (optional): Integer") @QueryParam("page") String page,
            @ApiParam("Pagination: number of records displayed per page (optional): Integer") @QueryParam("limit") String limit
    ) {

        Map<String, String> map1 = new HashMap<>();
        map1.put("name", name);
        map1.put("page", page);
        map1.put("limit", limit);

        List<Category> result = categoryService.findAll(map1);

        if (result.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        if (result.get(0).getId()== -500L)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.ok().body(result);
    }

    /**
     * Get category by ID
     * @param id Primary key of Category: Long
     * @return Category from database
     */
    @GetMapping("/categories/{id}")
    @ApiOperation("Get category by Id")
    public ResponseEntity<Category> findOne(@ApiParam("Primary key of category: Long") @PathVariable Long id){

        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<Category> resultOpt = categoryService.findOne(id);

        if(!resultOpt.isPresent())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        if (resultOpt.get().getId() == -500L)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.ok().body(resultOpt.get());
    }

}
