package com.wisform.service;

import com.wisform.dao.FillFormRepository;
import com.wisform.dao.PersonRepository;
import com.wisform.entity.Answer;
import com.wisform.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {
    @Autowired
    private FillFormRepository fillFormRepository;
    public boolean AnswerNode(Answer answer,List<String> item,List<String> itemvalue,String title,String filler) {
        Long n = answer.getId();
        //System.out.print(n);
        answer = fillFormRepository.createById(n,title,filler,item,itemvalue);
        if(answer!=null){
            fillFormRepository.Relate_Fill(filler);
            fillFormRepository.Relate_Contribute(title, filler);
            fillFormRepository.Relate_Own(title);
            return true;
        }else{
            return false;
        }

    }
}
