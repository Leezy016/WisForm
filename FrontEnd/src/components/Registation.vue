<template>
    <div class="w3l-hotair-form">

        <h1>欢 迎 注 册 WisForm！</h1>

        <div class="container">
            <div class="workinghny-form-grid">
                <div class="main-hotair">
                    <div class="content-wthree">

                        <h2>Sign up</h2>
                        <form @submit.prevent="handleSubmit">
                            <input type="text" class="text" name="text" placeholder="User Name" v-model="username" >
                            <input type="password" class="password" name="password" placeholder="User Password" v-model="password" >
                            <p v-if="loginError" class="error-message">{{ loginError }}</p>

                            <el-select
                            v-model="role"
                            placeholder="身份"
                            size="large"
                            style="width: 350px"
                            @change="checkDean"
                            class="sel"
                            >
                            <el-option
                            v-for="item in roleOptions"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                            />
                            </el-select>

                            <div v-if="isNotDean">  

                                <el-select
                            v-model="department"
                            placeholder="系别"
                            size="large"
                            style="width: 350px"
                            class="sel"
                            >
                            <el-option
                            v-for="item in departmentOptions"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                            />
                            </el-select>
                        </div>

                            <button class="btn" type="submit">Sign up</button>
                        </form>
                        
                        <p class="account">已有账号？ <a href="/login">请点此登陆</a></p>
                    </div>

                    <div class="w3l_form align-self">
                        <div class="left_grid_info">
                            <img src="@/assets/login2.png" >
                        </div>
                    </div>

                </div>
            </div>
        </div>
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
        roleOptions: [
  {
    value: 'dean',
    label: '院长',
  },
  {
    value: 'chair',
    label: '系主任',
  },
  {
    value: 'teacher',
    label: '老师',
  }
],
departmentOptions: [
  {
    value: 'CS',
    label: '计算机科学与技术',
  },
  {
    value: 'IS',
    label: '信息安全',
  }
]
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

<style>
.sel{
    margin-top: 10px;
    margin-bottom: 10px;
}

* {
    box-sizing: border-box;
}

.d-grid {
    display: grid;
}

.d-flex {
    display: flex;
    display: -webkit-flex;
}

.text-center {
    text-align: center;
}

.text-left {
    text-align: left;
}

.text-right {
    text-align: right;
}

button,
input,
select {
    outline: none;
    font-family: 'Noto Sans JP', sans-serif;
}

button,
.btn,
select {
    cursor: pointer;
}

a {
    text-decoration: none;
}

img {
    max-width: 100%;
}

ul {
    margin: 0;
    padding: 0
}

h1,
h2,
h3,
h4,
h5,
h6,
p {
    font-family: '黑体';
    margin: 0;
    padding: 0
}

p {
    color: #666;
    font-size: 16px;
    line-height: 25px;
    opacity: .6;
    text-align: center;
}

.p-relative {
    position: relative;
}

.p-absolute {
    position: absolute;
}

.p-fixed {
    position: fixed;
}

.p-sticky {
    position: sticky;
}

.btn,
button,
.actionbg,
input {
    border-radius: 36px;
    -webkit-border-radius: 36px;
    -moz-border-radius: 36px;
    -o-border-radius: 36px;
    -ms-border-radius: 36px;
}

.btn:hover,
button:hover {
    transition: 0.5s ease;
    -webkit-transition: 0.5s ease;
    -o-transition: 0.5s ease;
    -ms-transition: 0.5s ease;
    -moz-transition: 0.5s ease;
}

/*-- wrapper start --*/
.wrapper {
    width: 100%;
    padding-right: 15px;
    padding-left: 15px;
    margin-right: auto;
    margin-left: auto;
}

@media (min-width: 576px) {
    .wrapper {
        max-width: 540px;
    }
}

@media (min-width: 768px) {
    .wrapper {
        max-width: 720px;
    }
}

@media (min-width: 992px) {
    .wrapper {
        max-width: 960px;
    }
}

@media (min-width: 1200px) {
    .wrapper {
        max-width: 1140px;
    }
}

.wrapper-full {
    width: 100%;
    padding-right: 15px;
    padding-left: 15px;
    margin-right: auto;
    margin-left: auto;
}

/*-- //wrapper start --*/

/*-- form styling --*/
.w3l-hotair-form {
    position: relative;
    min-height: 100vh;
    z-index: 0;
    background: #6292ff;
    padding: 40px 40px;
    justify-content: center;
    display: grid;
    grid-template-rows: 1fr auto 1fr;
    align-items: center;
}
.container {
    max-width: 890px;
    margin: 0 auto;
}

.w3l_form {
    flex-basis: 50%;
    -webkit-flex-basis: 50%;
    background: #f4f9fd;
    background-size: cover;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    -ms-background-size: cover;
    padding: 40px;
    border-top-right-radius: 8px;
    border-bottom-right-radius: 8px;
    align-items: center;
    display: grid;
}

.content-wthree {
    flex-basis:50%;
    -webkit-flex-basis:50%;
    box-sizing: border-box;
    padding: 3em 3em;
    background: #fff;
    box-shadow: 2px 9px 49px -17px rgba(0, 0, 0, 0.1);
    border-top-left-radius: 8px;
    border-bottom-left-radius: 8px;
}

.w3l-workinghny-form .logo {
    text-align: center;
}

.w3l-hotair-form .main-hotair {
    position: relative;
    display: -webkit-box;
    display: -moz-box;
    display: -ms-flexbox;
    display: -webkit-flex;
    display: flex;
    margin: 40px 0;
}

.w3l-hotair-form form {
    margin-top: 30px;
    margin-bottom: 30px;
}

.social-icons {
    text-align: center;
    margin-top: 16px;
}
p.account, p.account a {
    text-align: center;
    padding-top: 20px;
    padding-bottom: 0px;
    font-size: 16px;
    color: #333;
}

p.account a{
    color:black;
}
p.account a:hover {
    text-decoration: underline;
}
.w3l-hotair-form h1 {
    text-align: center;
    font-size: 40px;
    font-weight: 700;
    color: white;
}

.w3l-hotair-form h2 {
    font-size: 30px;
    line-height: 40px;
    margin-bottom: 5px;
    font-weight: 900;
    color: #272346;
    text-align: center;
}

.w3l-hotair-form input{
    outline: none;
    margin-bottom: 15px;
    font-size: 16px;
    color: #999;
    text-align: left;
    padding: 14px 20px;
    width: 100%;
    display: inline-block;
    box-sizing: border-box;
    border: none;
    outline: none;
    background: #f7fafc;
    border: 1px solid #e5e5e5;
    transition: .3s ease;
    -webkit-transition: .3s ease;
    -moz-transition: .3s ease;
    -ms-transition: .3s ease;
    -o-transition: .3s ease;
}
.w3l-hotair-form input:focus {
    background:transparent;
    border: 1px solid #0568C1;
}
.w3l-hotair-form button {
    font-size: 18px;
    color: #fff;
    width: 100%;
    background: #6292ff;
    border: none;
    padding: 14px 15px;
    font-weight: 700;
    transition: .3s ease;
    -webkit-transition: .3s ease;
    -moz-transition: .3s ease;
    -ms-transition: .3s ease;
    -o-transition: .3s ease;
}

.w3l-hotair-form button:hover {
    background: #8eacfe;
}

.w3l-hotair-form .social-icons ul li {
    list-style: none;
    display: inline-block;
}

.w3l-hotair-form .social-icons ul li a {
    padding: 8px;
}

.w3l-hotair-form .social-icons ul li a:hover {
    opacity: 0.8;
    transition: 0.5s ease;
    -webkit-transition: 0.5s ease;
    -o-transition: 0.5s ease;
    -ms-transition: 0.5s ease;
    -moz-transition: 0.5s ease;
}

.w3l-hotair-form .social-icons ul span.fa {
    color: #696687;
    font-size: 18px;
    opacity: .8;
}

.w3l-hotair-form .social-icons ul li a.facebook span {
    font-size: 18px;
    width: 36px;
    height: 36px;
    border-radius: 50%;
    margin-right: 6px;
    border: 2px solid #696687;line-height:32px;
}

.w3l-hotair-form .social-icons ul li a.twitter span {
    color: #1da1f2;
    font-size: 18px;
    width: 36px;
    height: 36px;
    border-radius: 50%;
    margin-right: 6px;
    border: 2px solid #1da1f2;
    line-height:32px;
}

.w3l-hotair-form .social-icons ul li a.pinterest span {
    color:#e60023;
    font-size: 18px;
    width: 36px;
    height: 36px;
    border-radius: 50%;
    margin-right: 6px;
    border: 2px solid #e60023;
    line-height:32px;
}


.copyright p {
    text-align: center;
    font-size: 17px;
    line-height: 26px;
    color: #fff;
    opacity: 1;
}

p.copy-footer-29 a {
    color: #fff;
}

p.copy-footer-29 a:hover {
    color:#fdc500;
    transition: 0.5s ease;
    -webkit-transition: 0.5s ease;
    -o-transition: 0.5s ease;
    -ms-transition: 0.5s ease;
    -moz-transition: 0.5s ease;
}


/* -- placeholder --*/


/*-- responsive design --*/

@media (max-width:736px) {
    .w3l-hotair-form .main-hotair {
        flex-direction: column;
    }
    .w3l-hotair-form form {
        margin-top: 30px;
        margin-bottom: 10px;
    }
    .w3l_form {
        order: 2;
        border-radius: 0;
        border-bottom-left-radius: 8px;
        border-bottom-right-radius: 8px;
        border-top-right-radius: 0;
    }
    .content-wthree {
        order: 1;
        border-radius: 0;
        border-top-left-radius: 8px;
        border-top-right-radius: 8px;
    }
}

@media (max-width:568px) {
    .w3l-hotair-form h1 {
        font-size: 36px;
    }

    .w3l-hotair-form .main-hotair {
        margin: 30px 0;
    }

    .content-wthree {
        padding: 2.5em;
    }
}

@media (max-width:480px) {
    .w3l-hotair-form {
        padding: 40px 30px;
    }
    .w3l-hotair-form h1 {
        font-size: 26px;
    }
}

@media (max-width:384px) {
    .w3l-hotair-form {
        padding: 30px 15px;
    }

    .content-wthree {
        padding: 2em;
    }
    .w3l-hotair-form h2 {
        font-size: 22px;
        line-height: 32px;
    }

    .copyright p {
        font-size: 16px;
    }

}
</style>