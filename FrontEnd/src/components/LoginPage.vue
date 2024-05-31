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
      department:[],
      role:'',
      permissions:[],// 用户权限列表
      loginError: '' // 登录错误信息
    };
  },
  methods: {
    login() {
      //发送表单数据给后端
       axios.post('http://localhost:8080/api/login', {
        username: this.username,
        password: this.password,
      })
      .then(response=>{
        if (response.data.success) {
          this.$store.commit('SET_PERMISSIONS', response.data.permissions);
          this.$store.commit('SET_DEPARTMENT', response.data.department);
          this.$store.commit('SET_ROLE', response.data.role);
          this.$store.commit('SET_USERNAME', this.username);
          this.$router.push('/form-fill');
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
