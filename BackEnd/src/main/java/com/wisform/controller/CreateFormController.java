package com.wisform.controller;

import com.wisform.dao.FFormatRepository;
import com.wisform.dao.PersonRepository;
import com.wisform.entity.ApiResponse;
import com.wisform.entity.FFormat;
import com.wisform.entity.FFormatForm;
import com.wisform.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class CreateFormController {
    @Autowired
    private FFormatRepository fFormatRepository;
    @PostMapping("/createform")
    public ResponseEntity<?> createform(@RequestBody FFormatForm form) {
        String name = form.getname();
        System.out.print(name);
        String Publisher = form.getPublisher();
        //Date LanchDate = form.getLanchDate();
        List<String> Item = form.getItem();
        List<String> ItemType = form.getItemType();

        FFormat optionalFFormat = fFormatRepository.findByName(name);
        if (optionalFFormat!=null) {
            System.out.print("收集表已存在，请修改收集表名称");
            return ResponseEntity.ok().body(new ApiResponse(false, "收集表已存在，请修改收集表名称"));
        } else {
            FFormat newfFormat = new FFormat(Item,ItemType,Publisher,name);
            fFormatRepository.saveFFormat(name,Publisher,Item,ItemType);
            fFormatRepository.Relate_Publish(Publisher,name);
            return ResponseEntity.ok(new ApiResponse(true, "创建收集表成功"));
        }


    }
}
