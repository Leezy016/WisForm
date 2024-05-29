<template>
    <div class="form-fill"> 
      <div class="sidebar">
      <ul>
        <li v-if="roleArray.includes('1')"  @click="navigate('FormView')">查看表格</li>
        <li v-if="roleArray.includes('2')" @click="navigate('FormCreate')">创建表格</li>
        <li v-if="roleArray.includes('3')" @click="navigate('FillForm')">填写表格</li>
        <li v-if="roleArray.includes('4')" @click="navigate('UserManagement')">增删人员</li>
      </ul>
    </div> 
    <!-- 主内容区域 -->
    <div class="main-content">
      <h2>Welcome, {{ username }}!</h2>
      <router-view></router-view>
    </div>
    </div>
  </template>

  <script>
  export default {
    name:"FormFill",
    props:{
      username:{
        type:String,
        required:true
      },
      role:{
        type:String,
        required:true
      }
    },
    computed: {
    roleArray() {
      return this.role.split(',');
      }
    },
    methods: {
      navigate(routeName) {
        this.$router.push({ name: routeName });
      }
    },
  }  
  </script>

<style scoped>

</style>