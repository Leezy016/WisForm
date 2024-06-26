package com.wisform.controller;
import com.wisform.dao.PersonRepository;
import com.wisform.entity.ApiResponse;
import com.wisform.entity.LoginForm;
import com.wisform.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class LoginController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody LoginForm form) {
        // 在这里处理接收到的表单数据
        String username = form.getUsername();
        String password = form.getPassword();
        String identity;
        // 打印接收到的用户名，用于调试

        Person optionalPerson = personRepository.findByName(username);
        // 在这里进行登录验证逻辑，模拟简单的验证过程
        if (optionalPerson!=null ) {
            if(optionalPerson.getEnable()!=0){
                if (optionalPerson.getPasswd().equals(password)) {
                    identity = optionalPerson.getIdentity();
                    List<Object> permissions = new ArrayList<>();
                    List<String> department = new ArrayList<>();
                    String role = optionalPerson.getIdentity();
                    permissions = personRepository.getroleControl(identity);
                    department = optionalPerson.getDepartment();
                    ApiResponse response = new ApiResponse(true, "登录成功",role,permissions,department);
                    response.apiout();
                    return ResponseEntity.ok().body(response);
                }else{
                    return ResponseEntity.ok().body(new ApiResponse(false, "密码错误"));
                }
            }else{
                return ResponseEntity.ok().body(new ApiResponse(false, "账户被冻结！"));
            }
        } else {
            return ResponseEntity.ok().body(new ApiResponse(false, "用户名不存在"));
        }

    }
}
