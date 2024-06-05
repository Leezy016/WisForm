<template>  
    <div>
      <NavBar/>
    </div>
    <div class="form-change">  
      <p>{{ title }}</p>  
      <div v-for="(itemName, index) in this.$store.state.item" :key="index" class="form-group">  
        <label>{{ itemName }}</label>  
          <input v-model="this.$store.state.content[index]" />  
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
    props: ['id'], // 接收从父组件传递过来的
    data() {  
    return {  
      item:this.$store.state.item,
      content:this.$store.state.content,
      getErrorMessage:'',
      submitErrorMessage:''
    };  
    },  
    components: {
      NavBar,
    },
    methods:{
    submitForm() {  
      console.log(this.content);  
      axios.post('http://localhost:8080/formchange', {
        id:this.id,
        Item:this.item,
        ItemValue:this.content
      })  
      .then(response => {  
        if (response.data.success) { 
          //console.log('表单提交成功'); 
          alert('表单提交成功！');
          this.$router.push({ name: 'FormViewDetail', params: { title: this.title} });
        }
        else {  
          this.submitErrorMessage = response.data.message || '表单提交失败，请稍后再试'; 
          alert(this.submiterrorMessage); 
        }
      })  
      .catch(error => {  
        if (error.response) {  
          // 后端返回的错误信息
          this.submitErrorMessage = error.response.data;   
        }
        else {
          this.submitErrorMessage = '表单提交失败，请稍后再试';
        }
      });  
    },   
    goBack(){
      this.$router.push({ name: 'FormViewDetail', params: { title: this.title} });
    }, 
    }, 
    
    created() {   
      console.log("item=");
      console.log(this.item);
    },

  };  
  </script>

  <style>
  .form-detail{
    margin: auto;
  }
</style>
