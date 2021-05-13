package com.example.proyectobanca.controller;

import com.example.proyectobanca.model.User;
import com.example.proyectobanca.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.ws.rs.QueryParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get all users
     * @return List of users from database
     */
    @GetMapping("/users")
    @ApiOperation("Get all users")
    public ResponseEntity<List<User>> findAll(
            @ApiParam("User name for search (optional): String") @QueryParam("name") String name,
            @ApiParam("Pagination: page from which the records start to be displayed (optional): Integer") @QueryParam("page") String page,
            @ApiParam("Pagination: number of records displayed per page (optional): Integer") @QueryParam("limit") String limit
    ) {

        Map<String, String> map1 = new HashMap<>();
        map1.put("name", name);
        map1.put("page", page);
        map1.put("limit", limit);

        List<User> usersDB = userService.findAll(map1);

        if (usersDB.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if (usersDB.get(0).getId()== -500L)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.ok().body(usersDB);
    }
}
