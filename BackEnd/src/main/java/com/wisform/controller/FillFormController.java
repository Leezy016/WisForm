package com.wisform.controller;

import com.wisform.dao.FFormatRepository;
import com.wisform.dao.FillFormRepository;
import com.wisform.entity.ApiFResponse;
import com.wisform.entity.FFormat;
import com.wisform.entity.FFormatForm;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FillFormController {
    @Autowired
    private FillFormRepository fillFormRepository;
    @Autowired
    private FFormatRepository fFormatRepository;
    @PostMapping("/fillformlist")
    public ResponseEntity<?> formlist() {
        List<String> formlist = new ArrayList<>();
        formlist = fillFormRepository.formlist();
        System.out.print(formlist);
        ApiFResponse response = new ApiFResponse(true,"获取表单列表成功",formlist);

        return ResponseEntity.ok().body(response);
    }
    @PostMapping("/fillform-desplay")
    public ResponseEntity<?> formdesplay(@RequestBody FFormat fFormat) {
        String title = fFormat.getName();
        List<String>Item = new ArrayList<>();
        List<String>ItemType = new ArrayList<>();
        System.out.print(title);
        //FFormat ffFormat = fFormatRepository.findByName("论文采集表A");
        Item = fFormatRepository.getItemByName(title);
        System.out.print(Item);
        System.out.print(Item.size());
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
    //@PostMapping("/fillform-recieve")
    ///public ResponseEntity<?> fillform() {

        //ApiFResponse response = new ApiFResponse();
        //return ResponseEntity.ok().body(response);
    //}
}
