package com.peaksoft.SpringbootCrudFront.controller;

import com.peaksoft.SpringbootCrudFront.Model.Role;
import com.peaksoft.SpringbootCrudFront.Model.User;
import com.peaksoft.SpringbootCrudFront.Service.RoleService;
import com.peaksoft.SpringbootCrudFront.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/users")
public class UserRestController {
    private final UserService userService;
    private final RoleService roleService;

    public UserRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        try{
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getById(@PathVariable("userId") long userId) {
        try{
            return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{roleId}")
    public ResponseEntity<User> addRole(@PathVariable("roleId") long id, @RequestBody User user) {
        try{
            Set<Role> roles = new HashSet<>();
            Role role = roleService.getRoleByID(id);
            roles.add(role);
            user.setRoles(roles);
            return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<User> update(@PathVariable("roleId") long id, @RequestBody User user) {
        try{
            Set<Role> roles = new HashSet<>();
            Role role = roleService.getRoleByID(id);
            roles.add(role);
            user.setRoles(roles);

            return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity delete(@PathVariable("userId") long userId) {
        try{
            userService.remove(userId);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/info")
    public ResponseEntity infoPerson(Principal principal){
        try{
            return new ResponseEntity(this.userService.loadUserByUsername(principal.getName()),HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
