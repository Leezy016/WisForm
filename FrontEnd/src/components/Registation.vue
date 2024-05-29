<template>  
    <div class="registation">  
      <h2>注册</h2>  
      <form @submit.prevent="handleSubmit">  
        <div>  
          <label for="username">用户名:</label>  
          <input type="text" id="username" v-model="username" required>  
        </div>  
        <div>  
          <label for="password">密码:</label>  
          <input type="password" id="password" v-model="password" required>  
        </div>  
        <div>  
          <label for="role">身份:</label>  
          <select id="role" v-model="role" @change="checkDean" required>    
            <option value="dean">院长</option>    
            <option value="chair">系主任</option>   
            <option value="teacher">老师</option> 
          </select>  
        </div>  
        <div v-if="isNotDean">  
          <label for="department">系别:</label>    
          <select id="department" v-model="department">  
            <option value="CS">计算机科学与技术</option>  
            <option value="IS">信息安全</option>  
            <!-- 其他系别 -->  
          </select>  
        </div>  
        <button type="submit">注册</button>  
      </form>  
      <p v-if="errorMessage" class="errorMessage">{{ errorMessage }}</p>  
    </div>  
  </template>  
    
  <script>  
import router from '../router';
import axios from 'axios';

export default {  
  name: 'RegisterForm',  
  data() {  
    return {  
      username: '',  
      password: '',  
      role: 'dean', 
      department:'',
      isNotDean:false,
      errorMessage: '',  
    };  
  },  
  methods: {  
    handleSubmit() {  
      if (!this.username){
        this.errorMessage = '请填写用户名';  
        return;  
      } 
      if (!this.password){
        this.errorMessage = '请填写密码';  
        return;  
      }
      if (!this.role){
        this.errorMessage = '请填写用户身份';  
        return;  
      } 
      if (this.role!='dean' && !this.department){
        this.errorMessage = '请填写所处院系';  
        return;  
      } 


      this.errorMessage = '';  

      axios.post('http://localhost:8080/api/register', {  
        username: this.username,  
        password: this.password,  
        role: this.role , 
        department:this.department
      })  
      .then(response => {  
        if (response.data.success) {
        // 注册成功处理逻辑  
        console.log('注册成功');
        // 使用router.push跳转到/login页面  
        router.push('/login'); 
        alert('注册成功！');
        }else {  
          this.errorMessage = response.data.message || '注册失败了，请稍后再试';  
        }
      })  
      .catch(error => {  
        if (error.response) {  
          // 后端返回的错误信息
          this.errorMessage = error.response.data;   
        }else {
          this.errorMessage = '注册失败，请稍后再试';
        }
      });  
    }, 
    checkDean() {  
      // 当身份选择变化时，检查是否为院长 
      if(this.role==='dean')
      this.isNotDean = false;
      else
      this.isNotDean = true;
    },  
  },  
};  
  </script>  
    
  <style scoped>  
  .register-container {  
    display: flex;  
    flex-direction: column;  
    align-items: center;  
    justify-content: center;  
    height: 100vh;  
    padding: 20px;  
    box-sizing: border-box;  
  }  
    
  .error-message {  
    color: red;  
    margin-top: 10px;  
  }  
  </style>