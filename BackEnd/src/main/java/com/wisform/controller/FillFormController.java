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
    @PostMapping("/fillformlist")
    public ResponseEntity<?> formlist(@RequestBody LoginForm loginForm) {//改
        String name = loginForm.getUsername();
        String role = personRepository.findIdentityByName(name);
        //System.out.print(role);
        List<String> formlist = new ArrayList<>();
        formlist = fillFormRepository.findFFormatByRole(role);
        //System.out.print(formlist);
        ApiFResponse response = new ApiFResponse(true,"获取表单列表成功",formlist);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/fillform-desplay")
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
    //@PostMapping("/anto-fill")
    //public ResponseEntity<?> autofill() {
        //ApiFResponse response = new ApiFResponse();
        //return ResponseEntity.ok().body(response);
    //}
    @PostMapping("/fillform-recieve")
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
}
