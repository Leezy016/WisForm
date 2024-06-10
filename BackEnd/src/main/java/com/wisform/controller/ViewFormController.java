package com.wisform.controller;

import com.wisform.dao.AnswerRepository;
import com.wisform.dao.FFormatRepository;
import com.wisform.dao.FillFormRepository;
import com.wisform.dao.PersonRepository;
import com.wisform.entity.Answer;
import com.wisform.entity.AnswerForm;
import com.wisform.entity.ApiFResponse;
import com.wisform.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/viewform")
public class ViewFormController {

    public static String globals;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private FFormatRepository fFormatRepository;
    @Autowired
     private AnswerRepository answerRepository;
    @Autowired
    private FillFormRepository fillFormRepository;
    @Autowired
    private PersonRepository personRepository;
    @PostMapping("/select")
    public ResponseEntity<?> select(@RequestBody Map<String, Object> requestBody) {//不知道和前端对不对应
        String num = (String) requestBody.get("num");
        String name = (String) requestBody.get("name");
        globals = num;
        //System.out.print(num);
        //System.out.print(name);
        if (num.equals("1")) {//发布的
            List<String> Createlist = new ArrayList<>();
            Createlist = fFormatRepository.findFormatsByPublisherName(name);
            ApiFResponse response = new ApiFResponse(true,"获取问卷列表成功",Createlist);//名字要和前端对应吗
            return ResponseEntity.ok().body(response);
        } else if (num.equals("2")) {//填写的
            //人 填 问卷查找问卷
            List<String> Filllist = new ArrayList<>();
            Filllist = fFormatRepository.findFormatByPersonName(name);
            ApiFResponse response = new ApiFResponse(true,"获取问卷列表成功",Filllist);
            return ResponseEntity.ok().body(response);

        } else {//权限内可查看到
            String  identity = personRepository.findIdentityByName(name);
            //叫什么名字
            if (identity.equals("dean")){
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
    public ResponseEntity<?> getSum(@RequestBody Map<String, Object> requestBody) {
        String formatname = (String) requestBody.get("formatname");
        List<String> Answerlist = new ArrayList<>();
        Answerlist = answerRepository.findAnswersByFormatname(formatname);
        ApiFResponse response1 = new ApiFResponse(true,"获取回答个数成功",Answerlist);
        return ResponseEntity.ok(response1);
    }
    //加 f返回num getContent getSum
    //
    @PostMapping("/getContent")
    public ResponseEntity<?> getContent(@RequestBody Map<String, Object> requestBody) {
        String numm = (String) requestBody.get("num");
        String title = (String) requestBody.get("title");
        long num;
        //System.out.print(numm);
        if(numm!=null){
            num = Long.parseLong(numm);
            List<String> Itemlist = new ArrayList<>();
            Itemlist = answerRepository.findItemById(num,title);
            List<String> Valuelist = new ArrayList<>();
            Valuelist = answerRepository.findValueById(num,title);
            LocalDate ddl = answerRepository.checkdate(title);//查
            LocalDate time = LocalDate.now();//转为Date型
            //System.out.println("Current date: " + ddl); // 只打印 LocalDate

            if(Itemlist!= null){
                if(globals.equals("2") ){//还需加入ddl的判断
                    ApiFResponse response = new ApiFResponse(true,"获取回答成功",Itemlist, Valuelist , time.isBefore(ddl));
                    System.out.print(response.getChangeable());
                    return ResponseEntity.ok().body(response);
                }else{
                    ApiFResponse response = new ApiFResponse(true,"获取回答成功",Itemlist, Valuelist , time.isBefore(ddl));
                    System.out.print(response.getChangeable());
                    return ResponseEntity.ok().body(response);
                }
            }else{
                return ResponseEntity.ok().body(new ApiFResponse(false,"获取表单失败!"));
            }
        }else{
            return ResponseEntity.ok().body(new ApiFResponse(false,"获取表单空!"));
        }


    }

    @PostMapping("/form-change")
    public ResponseEntity<?> formchange(@RequestBody AnswerForm answerForm) {
        Long id = answerForm.getId();
        List<String> item = answerForm.getItem();
        List<String> itemvalue = answerForm.getValue();
        Answer answer = new Answer(id,item,itemvalue);
        if(answerService.converbyid(id,item,itemvalue)){
            ApiFResponse response = new ApiFResponse(true,"修改成功！");
            return ResponseEntity.ok().body(response);
        }else{
            ApiFResponse response = new ApiFResponse(false,"修改失败！");
            return ResponseEntity.ok().body(response);
        }
    }
    @PostMapping("/search")//未
    public ResponseEntity<?> search(@RequestBody Map<String, Object> requestBody) {
        String siterm = (String) requestBody.get("title");
        System.out.print(siterm+"\n");
        List<String> ans = answerRepository.findValuesByItemContaining(siterm);
        System.out.print(ans+"\n");
        ApiFResponse response1 = new ApiFResponse(true,"搜索成功",ans);
        return ResponseEntity.ok(response1);

    }
}