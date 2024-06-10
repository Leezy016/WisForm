package com.wisform.entity;

import java.util.*;

public class MappingTable_Project {
    private static final Map<String, List<String>> mappingTable = new HashMap<>();

    static {
        // 硬编码映射表
        mappingTable.put("name", Arrays.asList("论文标题", "标题","论文名称","名称","成果名称","获奖名称","项目名称","课程名称","论文","专著","专利","获奖","专著名称","专利名称","获奖名称"));
        mappingTable.put("journal", Arrays.asList("发表期刊", "期刊","发表刊物/出版社","发表刊物","刊物","出版社","出版单位"));
        mappingTable.put("date", Arrays.asList("发表年份及卷（期）数", "时间","立项时间"));
        mappingTable.put("time", Arrays.asList("项目起止年月", "起讫时间"));
<<<<<<< HEAD
        mappingTable.put("journalInclusion", Arrays.asList("期刊收录情况",""));
=======
        mappingTable.put("journal-inclusion", Arrays.asList("期刊收录情况",""));
>>>>>>> FrontEnd
        mappingTable.put("notes", Arrays.asList("备注", "备注"));
        mappingTable.put("institutes", Arrays.asList("投入单位名称",""));
        mappingTable.put("funds", Arrays.asList("项目经费", "到账经费"));
        mappingTable.put("sign", Arrays.asList("署名情况", "署名"));
        mappingTable.put("class hour", Arrays.asList("学时"));
<<<<<<< HEAD
        mappingTable.put("targetAudience", Arrays.asList("主要授课对象", "授课对象"));
=======
        mappingTable.put("target-audience", Arrays.asList("主要授课对象", "授课对象"));
>>>>>>> FrontEnd
        mappingTable.put("type", Arrays.asList("项目类别", "专利类型","获奖类别"));

    }

    //
    public static Map<String, List<String>> getMappingTable() {
        return mappingTable;
    }
    public String KeyCheck(String item) {
        Optional<String> foundKey = mappingTable.entrySet().stream()
                .filter(entry -> entry.getValue().contains(item))
                .map(Map.Entry::getKey)
                .findFirst();
        return foundKey.orElse(null);
    }
}
