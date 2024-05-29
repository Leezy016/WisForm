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
      4
    ],
    "teacher": [
      3
    ],
    "chair": [
      1,
      2,
      3,
      4
    ],
    "name": "rc"
  },
  "elementId": "4:ac49a548-b294-48ff-ada9-1e8a615aeee6:0"
}
//1-查看表格
//2-创建表格
//3-填写表格
//4-增删人员
```
### 创建语句
```Cypher
create (n:roleControl{name:'rc',dean:[1,2,3,4],chair:[1,2,3,4],teacher:[3]}) 
```
