<template>  
  <nav class = "sidebar">  
    <ul>  
      <p>
        Welcome,{{  }}
      </p>
      <!-- 使用 v-for 遍历 permissions 并根据权限显示不同的链接或文本 -->  
      <li v-for="permission in permissions" :key="permission">  
        <!-- 假设有一个 permissionMap 对象来映射权限ID到文本 -->  
        <span v-if="permissionMap[permission]">{{ permissionMap[permission] }}</span>   
      </li>  
    </ul>  
  </nav>  
</template>  
  
<script>  
export default {  
  name: 'NavBar',  
  computed: {  
    // 使用计算属性从 Vuex store 中获取 permissions  
    permissions() {  
      return this.$store.state.permissions;  
    },  
    // 可选的 permissionMap 对象，用于将权限ID映射到文本 
    permissionMap() {  
      return {  
        1: '查看表单',  
        2: '创建表单',  
        3: '填写表单',
        4: '管理人员'
      };  
    },  
  },  
};  
</script>

<style scoped>  
.sidebar {  
  position: fixed; /* 或者 absolute，取决于你的页面布局需求 */  
  top: 0;  
  left: 0;  
  bottom: 0; /* 如果你想让侧边栏占满整个页面高度 */  
  width: 200px; /* 设置侧边栏宽度 */  
  background-color: #f8f9fa; /* 设置背景颜色 */  
  overflow-y: auto; /* 如果内容太多，允许垂直滚动 */  
  padding-top: 20px; /* 顶部内边距，可选 */  
}  
  
.sidebar ul {  
  list-style-type: none;  
  padding: 0;  
  margin: 0;  
}  
  
.sidebar li {  
  padding: 10px; /* 设置列表项的内边距 */  
  border-bottom: 1px solid #ddd; /* 可选，设置列表项之间的分隔线 */  
}  
  
/* 如果你希望点击侧边栏链接时不产生默认的蓝色背景 */  
.sidebar li a {  
  color: inherit;  
  text-decoration: none;  
}  
  
</style>