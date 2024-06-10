<template>  
  <div class="home">  

    <div class="white-bar"></div>

    <nav class="navbar">
      <ul class="nav-links">
        <li><router-link to="/login" class="nav-item">去登陆</router-link></li>
        <li><router-link to="/regist" class="nav-item">去注册</router-link></li>
      </ul>
    </nav>
  
    <div class="logo">  
      <img src='@/assets/logo.png' style="width: 500px; height: 500px;"  />  
    </div>

  </div>  
</template> 


<style scoped>  

.navbar {
  background-color: #6292ff; /* 导航栏背景颜色 */
  padding: 5px 0; /* 导航栏上下内边距 */
}

.nav-links {
  list-style: none ; /* 去掉列表样式 */
  padding: 0;
  margin: 0;
  display: flex; /* 将链接排成一行 */
  justify-content: center; /* 居中对齐链接 */
}

.nav-item {
  margin: 80px; /* 间距 */
  font-family: "黑体";
  font-size: 20px;
}

.nav-links .nav-item {
  color: #fff ;
  text-decoration: none ;
}

.white-bar{
  padding: 25px ; /* 白色条的上下内边距 */
}

.logo {  
    text-align: center; /* 使图片居中 */  
}

</style>