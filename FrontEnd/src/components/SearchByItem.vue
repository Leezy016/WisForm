<template>
    <div class="search-by-item">
      <div>
        <NavBar/>
      </div>
      <div class="welcome">  
        <img src='@/assets/welcome.png' style="width: 500px; height: 80px;"  />  
        </div>
      <div class="text">
        <p style="color: black;">请输入需要查看的表项名</p>
      </div>

      <el-input v-model="item" style="width: 240px" placeholder="Please input" />
      <button type="button" class="search-btn" @click="search">搜索</button> 

    <div>  
    <table class="table" >  
      <thead>
        <tr><th>{{ title }}</th></tr>
      </thead>
      <tbody>  
        <tr v-for="(value, index) in content" :key="index">  
          <td>{{ value }}</td>  
        </tr>  
      </tbody>  
    </table>  
  </div>
</div>

</template>

<script>
import NavBar from './NavBar.vue'; 
import axios from 'axios';
export default {
  components: {
      NavBar,
    },
    data() {  
    return {  
      item:'',
      title:'',
      content:[],
      getErrorMessage:'',
    };  
    }, 
    methods:{
        search(){
        this.title=this.item;
        axios.post('http://localhost:8080/search', {  
        title:this.item
        })  
        .then(response => {  
          if (response.data.success) { 
            this.content=response.data.ans
          }
          else {  
            this.getErrorMessage = response.data.message || '信息获取失败，请稍后再试';  
          }
         })  
        .catch(error => {  
          if (error.response) {  
            this.getErrorMessage = error.response.data;   
          }
          else {
            this.getErrorMessage = '信息失败，请稍后再试';
          }
        });
        }
    }

}
</script>
<style>  
  .container {  
    display: flex;  
    flex-direction: row;  
  }  
    
  .table {  
  width: 100%; /* 表格宽度设置为100% */  
  border-collapse: collapse; /* 合并相邻的边框 */  
  margin-bottom: 20px; /* 底部外边距 */  
  margin-top: 30px;
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

  .search-by-item{  
    max-width: 400px;
    margin: auto;
  } 
  .search-btn {  
  margin-left: 10px; /* 左边距 */  
  padding: 8px 15px; /* 内边距 */  
  background-color: #6292ff; /* 蓝色背景 */  
  color: #fff; /* 白色文本 */  
  border: none; /* 无边框 */  
  border-radius: 8px; /* 圆角 */  
  cursor: pointer; /* 鼠标悬停时变为小手图标 */  
  transition: background-color 0.3s ease; /* 背景色过渡效果 */  
  margin-top: 7px;
}  
  
.search-btn:active {  
  background-color: #0056b3; /* 深蓝色背景 */  
} 
</style>