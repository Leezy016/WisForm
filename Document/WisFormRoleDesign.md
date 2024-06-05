### 角色控制访问

![](RoleDesign.png)

```json
// 角色控制访问表
{
  "identity": 0,
  "labels": [
    "roleControl"
  ],
  "properties": {
    "dean": [
      1,
      2,
      3,
      4,
      5,
      6,
      7
    ],
    "teacher": [
      1,
      2,
      6
    ],
    "chair": [
      1,
      2,
      3,
      4,
      5,
      6,
      7
    ],
    "name": "rc"
  },
  "elementId": "4:ac49a548-b294-48ff-ada9-1e8a615aeee6:0"
}
//         1: '查看表单',
//         2: '- 我填写的',
//         3: '- 我发布的',
//         4: '- 我可查看',
//         5: '创建表单',  
//         6: '填写表单',
//         7: '管理人员'
```
### 创建语句
```Cypher
create (n:roleControl{name:'rc',dean:[1,2,3,4,5,6,7],chair:[1,2,3,4,5,6,7],teacher:[1,2,6]}) 
```