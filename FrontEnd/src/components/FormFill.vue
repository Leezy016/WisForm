<template>  
  <div class="form-fill">  
    <NavBar/>
    <table class="table">  
      <thead>  
        <tr><th>请选择需要填写的表单</th></tr>  
      </thead>  
      <tbody>  
        <tr v-for="(title,index) in titles" :key="index">  
          <td>{{ title }}</td> 
          <button type="fill" class="fill-btn" @click="goToDetail(title)">填写</button> 
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
  data() {  
    return {  
      titles: [],
      getErrorMessage:'' 
    };  
  },  
  components: {
      NavBar,
  },
  methods: {  
    getTitles() {   
      axios.post('http://localhost:8080/fillformlist', {  
        })  
        .then(response => {  
          if (response.data.success) { 
            //console.log('表单提交成功'); 
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
            this.getErrorMessage = '表单提交失败，请稍后再试';
          }
        });  
    },  
    goToDetail(title) {  
      this.$router.push({ name: 'FormFillDetail', params: { title: title} });  
    }  
  },  
  created() {  
    this.getTitles(); // 在组件创建时获取数据  
  }  
};  
</script>

<style>  
.container {  
  display: flex;  
  flex-direction: row;  
}  
  
.form-fill{  
  max-width: 400px;
  margin: auto;
}  
</style>
