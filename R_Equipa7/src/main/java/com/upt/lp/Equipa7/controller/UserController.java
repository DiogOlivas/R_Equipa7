package com.upt.lp.Equipa7.controller;

import com.upt.lp.Equipa7.DTO.UserDTO;
import com.upt.lp.Equipa7.entity.User;
import com.upt.lp.Equipa7.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

    @RestController
    @RequestMapping("/users")
    public class UserController{
        private final UserService userService;

        public UserController(UserService userService){
            this.userService = userService;
        }
        @GetMapping 
        public List<User> getAll(){
            return userService.getAllUsers();
        }
        @GetMapping("/{id}")
        public User getById(@PathVariable Long id){
            return userService.getUser(id);
        }

        @PostMapping
        public User create(@RequestBody UserDTO dto) {
            return userService.createUser(dto);
       }

    
        @PutMapping("/{id}")
        public User update(@PathVariable Long id, @RequestBody UserDTO dto) {
            return userService.updateUser(id, dto);
        }

        @DeleteMapping("/{id}") 
        public void delete(@PathVariable Long id){
            userService.deleteUser(id);
        }

    
}
