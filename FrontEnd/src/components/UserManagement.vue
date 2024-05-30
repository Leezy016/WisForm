<template>
  <div class="user-management">
    <h2>用户管理</h2>
    <form @submit.prevent="addUser">
      <input type="text" v-model="newUser.username" placeholder="用户名">
      <input type="text" v-model="newUser.userType" placeholder="用户类型">
      <button type="submit">添加用户</button>
    </form>
    
    <table>
      <thead>
        <tr>
          <th>用户名</th>
          <th>用户类型</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(user, index) in users" :key="index">
          <td>{{ user.username }}</td>
          <td>{{ user.userType }}</td>
          <td>
            <button @click="editUser(user)">编辑</button>
            <button @click="deleteUser(user)">删除</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      users: [], // 用户列表
      newUser: { username: '', userType: '' } // 添加新用户表单数据
    };
  },
  mounted() {
    // 模拟从后端获取用户列表
    this.users = [
      { username: '用户1', userType: '管理员' },
      // 添加更多用户
    ];
  },
  methods: {
    addUser() {
      // 添加新用户
      this.users.push({ username: this.newUser.username, userType: this.newUser.userType });
      // 清空表单数据
      this.newUser.username = '';
      this.newUser.userType = '';
    },
    editUser(user) {
      // 编辑用户，可以弹出模态框进行编辑
      console.log('编辑用户:', user);
    },
    deleteUser(user) {
      // 删除用户，可以弹出确认对话框
      const index = this.users.indexOf(user);
      if (index !== -1) {
        this.users.splice(index, 1);
      }
    }
  }
};
</script>

<style scoped>
/* 样式 */
</style>
