package com.wisform.controller;
import com.wisform.entity.ApiResponse;
import com.wisform.entity.Person;
import com.wisform.entity.RegistForm;
import com.wisform.service.RegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegistController {

    @Autowired
    private RegistService registService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistForm form) {
        String username = form.getUsername();
        String password = form.getPassword();
        String identity = form.getIdentity();
        String department = form.getDepartment();
        Person user = new Person(username,identity,password);
        //System.out.println("check department: "+form.getDepartment());
        try {
            Person newUser = registService.registerUser(user,department);
            return ResponseEntity.ok(new ApiResponse(true, "注册成功"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

