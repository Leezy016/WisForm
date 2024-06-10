package com.wisform.controller;

import com.wisform.dao.AnswerRepository;
import com.wisform.dao.FFormatRepository;
import com.wisform.dao.FillFormRepository;
import com.wisform.dao.PersonRepository;
import com.wisform.entity.*;
import com.wisform.service.AnswerService;
import com.wisform.service.ReuseService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class FillFormController {
    @Autowired
    private FillFormRepository fillFormRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private ReuseService reuseService;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private FFormatRepository fFormatRepository;
<<<<<<< HEAD
    @PostMapping("/fillformlist")//返回可填的表单列表（role限制，ddl限制）
    public ResponseEntity<?> formlist(@RequestBody LoginForm loginForm) {//改
        //reuseService.Initial();
        String name = loginForm.getUsername();
        String role = personRepository.findIdentityByName(name);
        //System.out.print(role);
        List<String> formlist_ = new ArrayList<>();
        //role限制
        formlist_ = fillFormRepository.findFFormatByRole(role);
        //ddl限制
        LocalDate time = LocalDate.now();//转为Date型
        LocalDate ddl;
        List<String> formlist = new ArrayList<>();
        for (String item : formlist_) {
            ddl = answerRepository.checkdate(item);
            if(time.isBefore(ddl)){
                formlist.add(item);
            }
        }
=======
    @PostMapping("/fillformlist")
    public ResponseEntity<?> formlist(@RequestBody LoginForm loginForm) {//改
        String name = loginForm.getUsername();
        String role = personRepository.findIdentityByName(name);
        //System.out.print(role);
        List<String> formlist = new ArrayList<>();
        formlist = fillFormRepository.findFFormatByRole(role);
>>>>>>> FrontEnd
        //System.out.print(formlist);
        ApiFResponse response = new ApiFResponse(true,"获取表单列表成功",formlist);

        return ResponseEntity.ok().body(response);
    }

<<<<<<< HEAD
    @PostMapping("/fillform-desplay")//展示具体选中的表的内容
=======
    @PostMapping("/fillform-desplay")
>>>>>>> FrontEnd
    public ResponseEntity<?> formdesplay(@RequestBody FFormat fFormat) {
        reuseService.Initial();
        String title = fFormat.getName();
        List<String>Item = new ArrayList<>();
        List<String>ItemType = new ArrayList<>();
        //System.out.print(title);
        //FFormat ffFormat = fFormatRepository.findByName("论文采集表A");
        Item = fFormatRepository.getItemByName(title);
        //System.out.print(Item);
        //System.out.print(Item.size());
        if(Item!= null){
            ApiFResponse response = new ApiFResponse(Item,true,"获取表单成功");
            return ResponseEntity.ok().body(response);
        }else{
            return ResponseEntity.ok().body(new ApiFResponse(false,"获取表单失败!"));
        }

    }
<<<<<<< HEAD
    @PostMapping("/fillform-recieve")//接收填写的回答，并且赋予id保存回数据库
=======
    //@PostMapping("/anto-fill")
    //public ResponseEntity<?> autofill() {
        //ApiFResponse response = new ApiFResponse();
        //return ResponseEntity.ok().body(response);
    //}
    @PostMapping("/fillform-recieve")
>>>>>>> FrontEnd
    public ResponseEntity<?> fillform(@RequestBody AnswerForm answerForm) {
        reuseService.commitAll();
        String title = answerForm.getTitle();
        String filler = answerForm.getFiller();
        List<String> item = answerForm.getItem();
        //System.out.print(answerForm.getItem());
        List<String> itemvalue = answerForm.getValue();
        Long id = fillFormRepository.global_id();
        if(id!=null){
            id = id+1;
        }else{
            id=0L;
        }
        Answer answer = new Answer(id,item,itemvalue);
        if(answerService.AnswerNode(answer,item,itemvalue,title,filler)){
            ApiFResponse response = new ApiFResponse(true,"提交成功！");
            return ResponseEntity.ok().body(response);
        }else{
            ApiFResponse response = new ApiFResponse(false,"失败！");
            return ResponseEntity.ok().body(response);
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> FrontEnd
