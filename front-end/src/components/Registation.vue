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
          <select id="role" v-model="role" required>  
            <option value="user">普通用户</option>  
            <option value="admin">管理员</option>  
          </select>  
        </div>  
        <button type="submit">注册</button>  
      </form>  
      <p v-if="errorMessage" class="errorMessage">{{ errorMessage }}</p>  
    </div>  
  </template>  
    
  <script>  
  import router from '../router';
  export default {  
    name: 'RegisterForm',  
    data() {  
      return {  
        username: '',  
        password: '',  
        role: 'user',  
        errorMessage: '',  
      };  
    },  
    methods: {  
      handleSubmit() {  
        // 这里只是前端验证，实际注册逻辑需要发送到后端处理  
        if (!this.username || !this.password || !this.role) {  
          this.errorMessage = '请填写所有必填项';  
          return;  
        }  
    
        // 假设后端注册成功，清除错误信息  
        this.errorMessage = '';  
    
        // 这里应该发送一个请求到后端进行注册  
        // 例如使用 axios 或 fetch API  
        // 示例：  
        // axios.post('/api/register', {  
        //   username: this.username,  
        //   password: this.password,  
        //   role: this.role  
        // })  
        // .then(response => {  
        //   // 注册成功处理逻辑  
        //   console.log('注册成功');  
        // })  
        // .catch(error => {  
        //   // 注册失败处理逻辑  
        //   this.errorMessage = '注册失败，请稍后再试';  
        // });  

        // 使用router.push跳转到/login页面  
        router.push('/login'); 
    
        // 在这个示例中，我们仅模拟注册成功  
        alert('注册成功！');  
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