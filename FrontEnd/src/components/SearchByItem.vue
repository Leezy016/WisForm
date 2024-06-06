<template>
    <div class="search-by-item">
      <div>
        <NavBar/>
      </div>
      <p>请输入需要检索的表项名</p>
      <input v-model="item" />
      <button type="button" class="search-btn" @click="search">搜索</button> 

    <div>  
    <table>  
      <tbody>  
        <tr v-for="(value, index) in content" :key="index">  
          <td>{{ value }}</td>  
        </tr>  
      </tbody>  
    </table>  
  </div>
</div>

</template>

<script>
import NavBar from './NavBar.vue'; 
import axios from 'axios';
export default {
  components: {
      NavBar,
    },
    data() {  
    return {  
      item:'',
      content:[],
      getErrorMessage:'',
    };  
    }, 
    methods:{
        search(){
        axios.post('http://localhost:8080/search', {  
        title:this.item
        })  
        .then(response => {  
          if (response.data.success) { 
            this.content=response.data.ans
          }
          else {  
            this.getErrorMessage = response.data.message || '信息获取失败，请稍后再试';  
          }
         })  
        .catch(error => {  
          if (error.response) {  
            this.getErrorMessage = error.response.data;   
          }
          else {
            this.getErrorMessage = '信息失败，请稍后再试';
          }
        });
        }
    }

}
</script>
<style>  
  
.search-by-item{  
  max-width: 200px;
  margin: auto;
}  
</style>