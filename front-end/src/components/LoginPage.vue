<template>
  <div class="login">
    <h2>用户登录</h2>
    <form @submit.prevent="login">
      <div>
        <label for="username">用户名：</label>
        <input type="text" id="username" v-model="username" placeholder="请输入用户名" required>
      </div>
      <div>
        <label for="password">密码：</label>
        <input type="password" id="password" v-model="password" placeholder="请输入密码" required>
      </div>
      <div>
        <label>身份：</label>
        <label><input type="radio" v-model="identity" value="admin">管理员</label>
        <label><input type="radio" v-model="identity" value="user">普通用户</label>
      </div>
      <button type="submit">登录</button>
    </form>
    <p v-if="loginError" class="error-message">{{ loginError }}</p>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      username: '',
      password: '',
      identity: 'user', // 默认选择普通用户
      loginError: '' // 登录错误信息
    };
  },
  methods: {
    login() {
      //发送表单数据给后端
       axios.post('http://localhost:8080/api/login', {
        username: this.username,
        password: this.password,
        identity: this.identity
      })
      .then(response=>{
        //处理后端响应
         if (response.data.success) {
        // 登录成功


        
        }
       else {
        // 登录失败，显示错误信息
        this.loginError =  response.data.message || '登录失败，请稍后重试！';}
      })
       
    }
  }
};
</script>

<style scoped>
.login {
  max-width: 400px;
  margin: auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.login h2 {
  margin-bottom: 20px;
}

.login form div {
  margin-bottom: 10px;
}

.login form label {
  font-size:20px;
}

.login button {
  display: block;
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.login .error-message {
  margin-top: 10px;
  color: #dc3545;
}
</style>
