<template>
    <div>
      <NavBar/>
    </div>
  <div class="user-management">
    <table class="table">  
      <thead>  
        <tr><th>用户管理</th></tr>  
      </thead>  
      <tbody>  
        <tr v-for="(name,index) in names" :key="index" >  
          <td>{{ name }}</td>  
          <button type="view" class="view-btn" @click="deleteUser(name)">查看</button>
        </tr>  
      </tbody>  
    </table> 
    <p v-if="getErrorMessage" class="errorMessage">{{ getErrorMessage }}</p>
    </div>
</template>

<script>
import NavBar from './NavBar.vue'; 
import axios from 'axios';
export default {
  components: {
      NavBar
    },
    data() {  
    return {  
      username:'',
      names:[],
      roles:[],
      getErrorMessage:'',
      deleteErrorMessage:'',
    };  
  },  
  methods: {  
    getNames() {  
      axios.post('http://localhost:8080/viewform/select', {  
        username:this.$store.state.username
        })  
        .then(response => {  
          if (response.data.success) {  
            this.names=response.data.names
          }
          else {  
            this.getErrorMessage = response.data.message || '用户列表获取失败，请稍后再试';  
          }
         })  
        .catch(error => {  
          if (error.response) { 
            this.getErrorMessage = error.response.data;   
          }
          else {
            this.getErrorMessage = '用户列表获取失败，请稍后再试';
          }
        });  
    },  
    deleteUser(username) {  
      axios.post('http://localhost:8080/viewform/select', {  
        username:username
        })  
        .then(response => {  
          if (response.data.success) {  
            alert('删除用户成功');
          }
          else {  
            this.deleteErrorMessage = response.data.message || '删除用户失败，请稍后再试';  
          }
         })  
        .catch(error => {  
          if (error.response) { 
            this.deleteErrorMessage = error.response.data;   
          }
          else {
            this.deleteErrorMessage = '删除用户失败，请稍后再试';
          }
        });
    }  
  },  
  created() {  
    this.getNames(); // 在组件创建时获取数据  
  }  
};
</script>

<style scoped>
.user-management{
  max-width: 300px;
  margin: auto;
  }
</style>
