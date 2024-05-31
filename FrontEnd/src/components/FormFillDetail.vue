<template>  
    <div>  
      <h1>详情页面</h1>  
      <p>标题: {{ titleText }}</p>  
      <!-- 在这里添加其他详情页面的内容 -->  
    </div>  
  </template>  
    
  <script>  
  export default {  
    props: ['titile'] // 接收从父组件传递过来的 titleText  
    // created() {  
    //   this.getTitles(); // 在组件创建时获取数据  
    // },

  };  
  </script>