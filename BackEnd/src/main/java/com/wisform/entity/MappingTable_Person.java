package com.wisform.entity;

import java.util.*;

public class MappingTable_Person {
    private static final Map<String, List<String>> mappingTable = new HashMap<>();

    static {
        // 硬编码映射表
        mappingTable.put("name", Arrays.asList("姓名", "名字","作者姓名","作者","作者名"));
        mappingTable.put("identify", Arrays.asList("身份","职务","职称","职业","技术职务"));
        mappingTable.put("department", Arrays.asList("院系","所在院系"));
        mappingTable.put("authortype", Arrays.asList("作者类型","类型作者"));
        mappingTable.put("gender", Arrays.asList("性别","性"));
        mappingTable.put("birthday", Arrays.asList("出生年月","出生日期"));
        mappingTable.put("degree", Arrays.asList("最终学位或最终学历","学位","学历","最终学历","最终学位"));
        mappingTable.put("brief-intro", Arrays.asList("简介","骨干教师简介"));
        mappingTable.put("enroll-field", Arrays.asList("招生领域","招生范围"));
    }

    //
    public String KeyCheck(String item) {
        Optional<String> foundKey = mappingTable.entrySet().stream()
                .filter(entry -> entry.getValue().contains(item))
                .map(Map.Entry::getKey)
                .findFirst();
        return foundKey.orElse(null);
    }
    public static Map<String, List<String>> getMappingTable() {
        return mappingTable;
    }
}
