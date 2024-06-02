package com.wisform.service;

import com.wisform.dao.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    public List<String> GetAnswersId(String name) {
        // 调用仓库方法获取答案列表
        return answerRepository.findAnswersByFillerName(name);
    }
   // public List<String> GetAnswerItem(int num) {
        // 调用仓库方法获取答案列表
   //     return answerRepository.findAnswersByFillerName(name);
    //}
   // public List<String> GetAnswerValue(int num) {
        // 调用仓库方法获取答案列表
   //     return answerRepository.findAnswersByFillerName(name);
   // }
}
