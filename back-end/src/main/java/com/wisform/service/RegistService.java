package com.wisform.service;
import com.wisform.dao.PersonRepository;
import com.wisform.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class RegistService {
    @Autowired
    private PersonRepository userRepository;
    public Person registerUser(Person user,String a) {
        // 检查用户名是否已经存在
        if (userRepository.findByName(user.getUsername()) != null) {
            throw new RuntimeException("用户名已存在");
        }
        if(a.equals("")){
            List<String> departs=new ArrayList<>();
            departs.add("CS");
            departs.add("IS");
            user.setDepartment(departs);
        }else{
            List<String> departmentList = new ArrayList<>();
            departmentList.add(a);
            user.setDepartment(departmentList);
        }
        return userRepository.saveUser(user.getUsername(), user.getIdentity(), user.getPasswd(),user.getDepartment());
    }
}
