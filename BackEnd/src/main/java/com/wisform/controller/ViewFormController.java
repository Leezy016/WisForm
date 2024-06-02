package com.wisform.controller;

import com.wisform.dao.AnswerRepository;
import com.wisform.dao.FFormatRepository;
import com.wisform.dao.FillFormRepository;
import com.wisform.dao.PersonRepository;
import com.wisform.entity.ApiFResponse;
import com.wisform.entity.ApiFResponse1;
import com.wisform.entity.TwoArraysResponse;
import com.wisform.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/viewform")
public class ViewFormController {
    @Autowired
    private AnswerService answerService;
    @Autowired
    private FFormatRepository fFormatRepository;
    @Autowired
     private AnswerRepository answerRepository;
    @Autowired
    private PersonRepository personRepository;
    @PostMapping("/select")
    public ResponseEntity<?> select(@RequestParam int num, @RequestParam String name) {//不知道和前端对不对应

        if (num == 1) {//发布的
            List<String> Createlist = new ArrayList<>();
            Createlist = fFormatRepository.findFormatsByPublisherName(name);
            ApiFResponse response = new ApiFResponse(true,"获取问卷列表成功",Createlist);//名字要和前端对应吗
            return ResponseEntity.ok().body(response);
        } else if (num == 2) {//填写的
            //人 填 问卷查找问卷
            List<String> Filllist = new ArrayList<>();
            Filllist = fFormatRepository.findFormatByPersonName(name);
            ApiFResponse response = new ApiFResponse(true,"获取问卷列表成功",Filllist);
            return ResponseEntity.ok().body(response);

        } else {//权限内可查看到
            String  identity = personRepository.findIdentityByName(name);
            //叫什么名字
            if (identity=="chair"){
                List<String> AllFormatlist = new ArrayList<>();
                AllFormatlist = fFormatRepository.findAllNames();
                ApiFResponse response = new ApiFResponse(true,"获取所有问卷列表成功",AllFormatlist);
                return ResponseEntity.ok().body(response);
            }
            else{
                List<String> SameIdentityPersonName = personRepository.findNameByIdentity(identity);
                List<String> Createlist = new ArrayList<>();

                for (String name1 : SameIdentityPersonName) { // 使用for-each循环来简化代码
                    List<String> formats = fFormatRepository.findFormatsByPublisherName(name1);
                    Createlist.addAll(formats); // 将查询到的格式列表添加到Createlist中
                }
                ApiFResponse response = new ApiFResponse(true,"获取所有问卷列表成功",Createlist);
                return ResponseEntity.ok().body(response);
            }

        }
    }
    //
    @PostMapping("/getSum")
    public ResponseEntity<?> getSum(@RequestParam String formatname) {
        List<String> Answerlist = new ArrayList<>();
        Answerlist = answerRepository.findAnswersByFormatname(formatname);
        int numm = Answerlist.size();
        ApiFResponse1 response1 = new ApiFResponse1(true,"获取回答个数成功",numm);
        return ResponseEntity.ok(response1);
    }
    //加 f返回num getContent getSum
    //
    @PostMapping("/getContent")
    public ResponseEntity<?> select12(@RequestParam String formatname,@RequestParam int num) {
        List<String> Answerlist = new ArrayList<>();
        Answerlist = answerRepository.findAnswersByFormatname(formatname);
        List<String> Itemlist = new ArrayList<>();
        Itemlist = answerRepository.findItemById(Answerlist.get(num));
        List<String> Valuelist = new ArrayList<>();
        Valuelist = answerRepository.findValueById(Answerlist.get(num));
        TwoArraysResponse response = new TwoArraysResponse(true,"获取回答成功",Itemlist, Valuelist);//true mess
        return ResponseEntity.ok(response);
    }



}
