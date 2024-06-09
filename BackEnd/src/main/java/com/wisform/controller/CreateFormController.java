package com.wisform.controller;

import com.wisform.dao.FFormatRepository;
import com.wisform.dao.PersonRepository;
import com.wisform.entity.ApiResponse;
import com.wisform.entity.FFormat;
import com.wisform.entity.FFormatForm;
import com.wisform.entity.Person;
import com.wisform.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class CreateFormController {
    @Autowired
    private FFormatRepository fFormatRepository;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    @PostMapping("/createform")
    public ResponseEntity<?> createform(@RequestBody FFormatForm form) {
        String name = form.getname();
        String Publisher = form.getPublisher();
        String ddl_s = form.getDDl();//处理DDL日期数据
        LocalDate ddl = LocalDate.parse(ddl_s, formatter);
        List<String> Item = form.getItem();
        List<String> roleList = form.getRoleList();
        boolean only = form.getOnly();
        List<String> ItemType = form.getItemType();
        FFormat optionalFFormat = fFormatRepository.findByName(name);
        List<String> newList = new ArrayList<>();
        if ("true".equals(roleList.get(0))) {
            newList.add("dean");
        }
        if ("true".equals(roleList.get(1))) {// 第二个位置
            newList.add("chair");
        }
        if ("true".equals(roleList.get(2))) {// 第三个位置
            newList.add("teacher");
        }
        if (optionalFFormat!=null) {
            return ResponseEntity.ok().body(new ApiResponse(false, "收集表已存在，请修改收集表名称"));
        } else {
            //FFormat newfFormat = new FFormat(Item,ItemType,Publisher,name);
            fFormatRepository.saveFFormat(name,Publisher,Item,ItemType,newList,only,ddl);
            fFormatRepository.Relate_Publish(Publisher,name);
            return ResponseEntity.ok(new ApiResponse(true, "创建收集表成功"));
        }


    }
}
