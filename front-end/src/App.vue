//App.vue充当整个应用程序的路由容器，<router-view></router-view>用来显示当前对应的组件内容
<template>
  <div class="app">
    <nav class="navbar">
      <ul class="nav-links">
        <li><router-link to="/" class="nav-item">回首页</router-link></li>
        <li><router-link to="/login" class="nav-item">去登陆</router-link></li>
      </ul>
    </nav>
    <div class="white-bar"></div>
    <router-view></router-view>
  </div>
</template>

<style>

.navbar {
  background-color: #5599ff; /* 导航栏背景颜色 */
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

  margin: 50px; /* 间距 */
}

.nav-links .nav-item {
  color: #fff ;
  text-decoration: none ;
}

.white-bar{
  padding: 25px 0; /* 白色条的上下内边距 */
}

.app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

