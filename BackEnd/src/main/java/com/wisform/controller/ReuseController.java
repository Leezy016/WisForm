package com.wisform.controller;

<<<<<<< HEAD
import com.wisform.dao.FFormatRepository;
=======
>>>>>>> FrontEnd
import com.wisform.entity.ApiFResponse;
import com.wisform.entity.ApiResponse;
import com.wisform.entity.MappingTable_Person;
import com.wisform.entity.MappingTable_Project;
import com.wisform.service.AnswerService;
import com.wisform.service.ReuseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static com.wisform.service.ReuseService.mappingTablePerson;
import static com.wisform.service.ReuseService.mappingTableProject;

@RestController
public class ReuseController {

    @Autowired
    private ReuseService reuseService;
<<<<<<< HEAD
    @Autowired
    private FFormatRepository fFormatRepository;
    @PostMapping("/key-match")
    public ResponseEntity<?> keymatch(@RequestBody Map<String, Object> requestBody){
        System.out.print("get in keymatch\n");
        String item = (String) requestBody.get("item");
        System.out.print(item+"\n");
        String title = (String) requestBody.get("title");
        System.out.print(title+"\n");
        String username = (String) requestBody.get("username");
        System.out.print(username+"\n");
        String itemValue = (String) requestBody.get("itemValue");
        MappingTable_Person mappingTablePerson = new MappingTable_Person();
        MappingTable_Project mappingTableProject = new MappingTable_Project();
        String key = mappingTablePerson.KeyCheck(item);
//System.out.print("personkey："+key+" value\n"+itemValue+"\n");
        if(key!=null){
            String only = fFormatRepository.OnlyByName(title);
            System.out.print(only);
            if(only!=null){
                if(itemValue.equals(username)){
                    reuseService.personKey(key,itemValue);
                    ApiFResponse response = new ApiFResponse(true,"");
                    return ResponseEntity.ok().body(response);
                }else{
                    ApiFResponse response = new ApiFResponse(false,"");
                    return ResponseEntity.ok().body(response);
                }
            }else{
                reuseService.personKey(key,itemValue);
                ApiFResponse response = new ApiFResponse(true,"");
                return ResponseEntity.ok().body(response);
            }
=======
    @PostMapping("/key-match")
    public void keymatch(@RequestBody Map<String, Object> requestBody){
        System.out.print("get in keymatch\n");
        String item = (String) requestBody.get("item");
        String itemValue = (String) requestBody.get("itemValue");//??对接口
        MappingTable_Person mappingTablePerson = new MappingTable_Person();
        MappingTable_Project mappingTableProject = new MappingTable_Project();
        String key = mappingTablePerson.KeyCheck(item);
        System.out.print("personkey："+key+" value\n"+itemValue+"\n");
        if(key!=null){
            reuseService.personKey(key,itemValue);
>>>>>>> FrontEnd
        }else{
            key = mappingTableProject.KeyCheck(item);//项目匹配
            System.out.print("projectkey："+key+" value\n"+itemValue+"\n");
            reuseService.projectKey(key,itemValue);
<<<<<<< HEAD
            ApiFResponse response = new ApiFResponse(true,"");
            return ResponseEntity.ok().body(response);
=======
>>>>>>> FrontEnd
        }
    }

    @PostMapping("/match")
    public ResponseEntity<?> match(@RequestBody Map<String, Object> requestBody){
        //System.out.print("get in match\n");
        String item = (String) requestBody.get("item");
        String itemValue = (String) requestBody.get("itemValue");
        String ans;
        String key = mappingTablePerson.KeyCheck(item);
        //System.out.print("personkey_："+key+" value_： "+itemValue+"\n");
        if(itemValue==null){
            System.out.print("get in auto match\n");

            if(key!=null){
                System.out.print("personkey_："+key+"\n");
                ans = reuseService.personMatch(key);
                ApiFResponse response = new ApiFResponse(true,"复用成功！",ans);
                return ResponseEntity.ok().body(response);
            }else {
                key = mappingTableProject.KeyCheck(item);//项目匹配
                System.out.print("projectkey_：" + key + "\n");
                ans = reuseService.projectMatch(key);
                ApiFResponse response = new ApiFResponse(true, "复用成功！", ans);
                return ResponseEntity.ok().body(response);
            }
        }
        else{
            //System.out.print("savakey: "+key+" saveValue: "+itemValue+"\n");
            if(key!=null){
                reuseService.savePerCouple(key,itemValue);
            }else{
                key = mappingTableProject.KeyCheck(item);
                reuseService.saveProCouple(key,itemValue);
            }
            ApiFResponse response = new ApiFResponse(false," ");
            return ResponseEntity.ok().body(response);
        }
    }

<<<<<<< HEAD
}
=======
}
>>>>>>> FrontEnd
