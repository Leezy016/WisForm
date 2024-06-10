<template>  
    <div class="form-fill">  
      <NavBar/>
      <div class="welcome">  
        <img src='@/assets/welcome.png' style="width: 500px; height: 80px;"  />  
        </div>
      <div class="text">
        <p style="color: black;">请选择需要填写的表单</p>
      </div>
      <table class="table">  
        <thead>  
          <tr><th>表单标题</th></tr>  
        </thead>  
        <tbody>  
          <tr v-for="(title,index) in titles" :key="index">  
            <td>{{ title }}</td> 
            <button type="fill" class="fill-btn" @click="goToDetail(title)">填 写</button> 
          </tr>  
        </tbody>  
      </table> 
      <p v-if="getErrorMessage" class="errorMessage">{{ getErrorMessage }}</p> 
    </div>  
  </template>
  
  <script>
  import NavBar from '../components/NavBar.vue'; 
  import axios from 'axios';
  export default {  
    data() {  
      return {  
        titles: [''],
        getErrorMessage:'' 
      };  
    },  
    components: {
        NavBar,
    },
    methods: {  
      getTitles() {   
        axios.post('http://localhost:8080/fillformlist', {  
          username:this.$store.state.username
          })  
          .then(response => {  
            if (response.data.success) { 
              //console.log('表单提交成功'); 
              this.titles=response.data.titles
            }
            else {  
              this.getErrorMessage = response.data.message || '表单列表获取失败，请稍后再试';  
            }
           })  
          .catch(error => {  
            if (error.response) {  
              // 后端返回的错误信息
              this.getErrorMessage = error.response.data;   
            }
            else {
              this.getErrorMessage = '表单提交失败，请稍后再试';
            }
          });  
      },  
      goToDetail(title) {  
        this.$router.push({ name: 'FormFillDetail', params: { title: title} });  
      }  
    },  
    created() {  
      this.getTitles(); // 在组件创建时获取数据  
    }  
  };  
  </script>
  
  <style> 
  .text{
    font-family: '黑体';
    padding: 0;
    color: black;
    font-size: 20px;
    line-height: 30px;
    opacity: .6;
    text-align: center;
    margin-top: 10px;
    margin-bottom: 20px;
  } 

  .container {  
    display: flex;  
    flex-direction: row;  
  }  
    
  .form-fill{  
    max-width: 400px;
    margin: auto;
  }  
  /* 表格基础样式 */  
.table {  
  width: 100%; /* 表格宽度设置为100% */  
  border-collapse: collapse; /* 合并相邻的边框 */  
  margin-bottom: 20px; /* 底部外边距 */  
}  
  
/* 表格头部样式 */  
.table thead th {  
  background-color: #f2f2f2; /* 浅灰色背景 */  
  color: #333; /* 文本颜色 */  
  padding: 10px; /* 内边距 */  
  border-bottom: 1px solid #ddd; /* 底部边框 */  
  text-align: left; /* 文本左对齐 */
    font: '黑体' sans-serif;
}  
  
/* 表格主体样式 */  
.table tbody tr {  
  border-bottom: 1px solid #ddd; /* 底部边框 */  
}  
  
.table tbody td {  
  padding: 10px; /* 内边距 */  
  vertical-align: top; /* 垂直对齐方式 */  
}  
  
/* 填写按钮样式 */  
.table .fill-btn {  
  margin-left: 10px; /* 左边距 */  
  padding: 5px 10px; /* 内边距 */  
  background-color: #6292ff; /* 蓝色背景 */  
  color: #fff; /* 白色文本 */  
  border: none; /* 无边框 */  
  border-radius: 8px; /* 圆角 */  
  cursor: pointer; /* 鼠标悬停时变为小手图标 */  
  transition: background-color 0.3s ease; /* 背景色过渡效果 */  
  margin-top: 7px;
}  
  
/* 填写按钮点击后的样式（可选） */  
.table .fill-btn:active {  
  background-color: #0056b3; /* 深蓝色背景 */  
}
  </style>
  