package com.wisform.controller;

import com.wisform.dao.FFormatRepository;
import com.wisform.dao.FillFormRepository;
import com.wisform.entity.ApiFResponse;
import com.wisform.entity.FFormat;
import com.wisform.entity.FFormatForm;
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
    @PostMapping("/fillform-list")
    public ResponseEntity<?> fillform() {
        List<String> formlist = new ArrayList<>();
        formlist = fillFormRepository.formlist();
        ApiFResponse response = new ApiFResponse(formlist);
        return ResponseEntity.ok().body(response);
    }
    //@PostMapping("/fillform")
    //public ResponseEntity<?> fillform(String title) {

    //}
}
