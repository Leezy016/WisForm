package com.wisform.controller;

import com.wisform.dao.PersonRepository;
import com.wisform.entity.ApiResponse;
import com.wisform.entity.RegistForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/manage")
public class ManageController {
    @Autowired
    PersonRepository personRepository;
    @PostMapping("/get-list")
    public ResponseEntity<?> getmanagelist(@RequestBody Map<String, Object> requestBody) {
        String username = (String) requestBody.get("username");
        String role = personRepository.findIdentityByName(username);
<<<<<<< HEAD
        System.out.print("role: "+role+"\n");
=======
>>>>>>> 1d977f80 (finish all)
        List<String> names = new ArrayList<>();
        if(role!=null && role.equals("dean")){
            names = personRepository.findPDean("dean");
            ApiResponse response = new ApiResponse(true, "获取人员列表成功！",names,1);
            return ResponseEntity.ok().body(response);
        }else if(role.equals("chair")){
            List<String> department = personRepository.findDepartmentByName(username);
<<<<<<< HEAD
            System.out.print(department+"\n");
=======
>>>>>>> 1d977f80 (finish all)
            String de = department.get(0);
            names = personRepository.findPChair(de);
            ApiResponse response = new ApiResponse(true, "获取人员列表成功！",names,1);
            return ResponseEntity.ok().body(response);
        }else{
            ApiResponse response = new ApiResponse(false, "获取人员列表失败！",names,1);
            return ResponseEntity.ok().body(response);
        }

    }

<<<<<<< HEAD
    @PostMapping("/action1")
=======
    @PostMapping("/action")
>>>>>>> 1d977f80 (finish all)
    public ResponseEntity<?> manage(@RequestBody Map<String, Object> requestBody){
        String username = (String) requestBody.get("username");
        personRepository.unableByName(username);
        ApiResponse response = new ApiResponse(true, "冻结人员成功！");
        return ResponseEntity.ok().body(response);
    }
<<<<<<< HEAD
    @PostMapping("/action2")
    public ResponseEntity<?> manage2(@RequestBody Map<String, Object> requestBody){
        String username = (String) requestBody.get("username");
        personRepository.ableByName(username);
        ApiResponse response = new ApiResponse(true, "解冻人员成功！");
        return ResponseEntity.ok().body(response);
    }
=======
>>>>>>> 1d977f80 (finish all)





}
