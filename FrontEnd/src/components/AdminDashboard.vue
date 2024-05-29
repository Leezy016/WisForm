<template>
  <div class="admin-dashboard">
    <h2>管理员控制台</h2>
    <router-link to="/data-view">数据查看</router-link>
    <router-link to="/user-management">用户管理</router-link>
    <!-- 其他控制台功能 -->
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  // 其他逻辑
};
</script>

<style scoped>
/* 样式 */
</style>
