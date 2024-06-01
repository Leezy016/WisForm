<template>  
    <div>
      <NavBar/>
    </div>
    <div class="form-detail">  
      <p>{{ title }}</p>  
      <!-- 在这里添加其他详情页面的内容 -->  
      <div v-for="(itemName, index) in item" :key="index" class="form-group">  
        <label>{{ itemName }}</label>  
          <input v-model="content[index]" />  
        </div>  
        <button type="button" class="submit-btn" @click="submitForm">提交</button> 
        <button type="button" class="return-btn" @click="goBack">返回</button>
        <p v-if="getErrorMessage" class="errorMessage">{{ getErrorMessage }}</p> 
        <p v-if="submitErrorMessage" class="errorMessage">{{ submitErrorMessage }}</p> 
    </div>  
  </template>  
    
  <script>  
  import NavBar from './NavBar.vue'; 
  import axios from 'axios';
  export default {  
    props: ['title'], // 接收从父组件传递过来的 title 
    data() {  
    return {  
      item: [],
      content:[],
      getErrorMessage:'',
      submitErrorMessage:''
    };  
    },  
    components: {
      NavBar,
    },
    methods:{
    getItems() {   
      axios.post('http://localhost:8080/api/register', {
        title:this.title
      })  
      .then(response => {  
        if (response.data.success) { 
          //console.log('表单内容获取成功'); 
          this.item=response.data.item
        }
        else {  
          this.getErrorMessage = response.data.message || '表单内容获取失败，请稍后再试';  
        }
      })  
      .catch(error => {  
        if (error.response) {  
          // 后端返回的错误信息
          this.getErrorMessage = error.response.data;   
        }
        else {
          this.getErrorMessage = '表单内容获取失败，请稍后再试';
        }
      });  
    },  
    submitForm() {  
      console.log(this.content);  
      axios.post('http://localhost:8080/api/register', {
        item:this.item,
        content:this.content
      })  
      .then(response => {  
        if (response.data.success) { 
          //console.log('表单提交成功'); 
        }
        else {  
          this.getErrorMessage = response.data.message || '表单提交失败，请稍后再试';  
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
    goBack(){
      this.$router.push('/form-fill');
    }, 
    }, 
    
    created() {  
      //this.getItems(); 
      this.content = Array(this.item.length).fill(''); 
    },

  };  
  </script>

  <style>
  .form-detail{
    margin: auto;
  }
</style>