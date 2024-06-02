<template>  
  <nav class = "sidebar">  
    <ul>  
      <!-- 使用 v-for 遍历 permissions 并根据权限显示不同的文本 -->  
      <li v-for="permission in permissions" :key="permission">  
        <a v-if="textMap[permission]" :href="linkMap[permission]">  
          {{ textMap[permission] }}  
        </a>
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
    textMap() {  
      return {  
        1: '查看表单',
        2: '- 我填写的',
        3: '- 我发布的',
        4: '- 我可查看',
        5: '创建表单',  
        6: '填写表单',
        7: '管理人员'
      };  
    },
    linkMap() {  
      return {  
        //1: '/form-view',
        2: '/form-view/2',
        3: '/form-view/1',
        4: '/form-view/3',
        5: '/form-create',  
        6: '/form-fill',
        7: '/user-management'
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