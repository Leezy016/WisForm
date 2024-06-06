<template>  
  <div class="form-view">  
      <NavBar/>
    <table class="table">  
      <thead>  
        <tr><th>请选择需要查看的表单</th></tr>  
      </thead>  
      <tbody>  
        <tr v-for="(title,index) in titles" :key="index" >  
          <td>{{ title }}</td>  
          <button type="view" class="view-btn" @click="goToDetail(title)">查看</button>
        </tr>  
      </tbody>  
    </table> 
    <p v-if="getErrorMessage" class="errorMessage">{{ getErrorMessage }}</p> 
  </div>  
</template>

<script>
import NavBar from '../components/NavBar.vue'; 
import axios from 'axios';
export default {  
  props: ['num'],
  data() {  
    return {  
      titles: ['titile1'],
      getErrorMessage:'' 
    };  
  },  
  components: {
      NavBar,
  },
  methods: {  
    getTitles() {  
      axios.post('http://localhost:8080/viewform/select', {  
        num:this.num,
        name:this.$store.state.username
        })  
        .then(response => {  
          if (response.data.success) {  
            this.titles=response.data.titles
          }
          else {  
            this.getErrorMessage = response.data.message || '表单列表获取失败，请稍后再试';  
          }
         })  
        .catch(error => {  
          if (error.response) {  
            // 后端返回的错误信息
            this.getErrorMessage = error.response.data;   
          }
          else {
            this.getErrorMessage = '表单列表获取失败，请稍后再试';
          }
        });  
    },  
    goToDetail(title) {  
      this.$router.push({ name: 'FormViewDetail', params: { title: title} });  
    }  
  },  
  created() {  
    this.getTitles(); // 在组件创建时获取数据  
    //console.log(this.titles);
  }  
};  
</script>

<style>  
.container {  
  display: flex;  
  flex-direction: row;  
}  
  
.form-view{  
  max-width: 400px;
  margin: auto;
}  
</style>
