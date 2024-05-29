<template>  
    <div class="nav-bar">  
      <!-- 导航栏内容 -->  
      <ul>  
        <li><router-link to="/form-view">查看表单</router-link></li>  
        <li><router-link to="/form-creat">创建表单</router-link></li> 
        <li><router-link to="/form-fill">填写表单</router-link></li> 
        <li><router-link to="/user-management">增删人员</router-link></li>  
        <!-- 其他链接 -->  
      </ul>  
    </div>  
  </template>  
    
    <script>  
    export default {  
      computed: {  
        // 假设permissions保存在Vuex中  
        permissions() {  
          return this.$store.state.permissions;  
        },  
        filteredPermissions() {  
          // 根据你的需求过滤和映射permissions到导航项  
          return this.permissions.map(permission => ({  
            id: permission.id, // 假设每个权限有唯一的id  
            name: permission.name, // 假设每个权限有显示的名称  
            path: permission.path // 假设每个权限关联一个路由路径  
            // ... 其他需要的属性  
          }));  
        }  
      }  
    };  
    </script> 
    
  <style scoped>  
  /* 样式 */  
  </style>