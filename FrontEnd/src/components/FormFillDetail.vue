<template>  
    <div>
      <NavBar/>
    </div>
    <div class="form-detail">  
      <p>{{ title }}</p>  

      <div v-for="(itemName, index) in item" :key="index" class="form-group">  
        <label>{{ itemName }}</label>  
          <input v-model="content[index]" @focus="keyJudge(item[index],content[index],index)"  @blur="keyMatch(item[index],content[index])" />
      </div>  

        <button type="button" class="submit-btn" @click="submitForm">提交</button> 
        <button type="button" class="return-btn" @click="goBack">返回</button>
        <p v-if="getErrorMessage" class="errorMessage">{{ getErrorMessage }}</p> 
        <p v-if="submitErrorMessage" class="errorMessage">{{ submitErrorMessage }}</p> 
    </div>  
  </template>  
    
  <script>  
  import NavBar from './NavBar.vue'; 
  import axios from 'axios';
  export default {  
    props: ['title'], // 接收从父组件传递过来的 title 
    data() {  
    return {  
      item: [],
      content:[],
      getErrorMessage:'',
      submitErrorMessage:'',
      matchErrorMessage:'',
      keys:["姓名","作者姓名","项目名称","论文标题"],
      selectedValue: '',
      isKey:false,
    };  
    },  
    components: {
      NavBar,
    },
    methods:{
      onValueChange() {  
      // 当选中值改变时触发的方法（可选）  
      console.log('Selected value changed to:', this.selectedValue);  
    },  
    Match(item, itemValue,index) {  
      console.log(`match for item: ${item}, value: ${itemValue}`); 
      this.content[index]="院长";
      // axios.post('http://localhost:8080/key-match', {  
      //   item:item,
      //   itemValue:itemValue
      //   })  
      //   .then(response => {  
      //     if (response.data.success) {  
      //       this.content[index]=response.data.itemValue
      //     }
      //    })
    }, 
    keyMatch(item, itemValue) {    
      if(this.isKey){
        console.log(`sent item: ${item}, value: ${itemValue}`);
      // axios.post('http://localhost:8080/key-match', {  
      //   item:item,
      //   itemValue:itemValue
      //   })  
      //   .then(response => {  
      //     if (response.data.success) {  
      //       this.content[index]=response.data.itemValue
      //     }
      //    })
        this.isKey=false;
      }
    },
    keyJudge(item,itemValue,index){  
      for (let i = 0; i < this.keys.length; i++) {   
        if (this.keys[i] === item) {  
          this.isKey = true;  
          break;  
        }  
      }  
      if (this.isKey===false) {  
        this.Match(item, itemValue,index);  
      }  
    },
    getItems() {   
      axios.post('http://localhost:8080/fillform-desplay', {
        name:this.title,
      })  
      .then(response => {  
        if (response.data.success) { 
          this.item=response.data.item;
        }
        else {  
          this.getErrorMessage = response.data.message || '表单内容获取失败，请稍后再试';  
        }
      })  
      .catch(error => {  
        if (error.response) {  
          // 后端返回的错误信息
          this.getErrorMessage = error.response.data;   
        }
        else {
          this.getErrorMessage = '表单内容获取失败，请稍后再试';
        }
      });  
    },  
    submitForm() {  
      console.log(this.content);  
      axios.post('http://localhost:8080/fillform-recieve', {
        title:this.title,
        filler:this.$store.state.username,
        Item:this.item,
        ItemValue:this.content
      })  
      .then(response => {  
        if (response.data.success) { 
          //console.log('表单提交成功'); 
          alert('表单提交成功！');
          this.$router.push('/form-fill');
        }
        else {  
          this.submitErrorMessage = response.data.message || '表单提交失败，请稍后再试'; 
          alert(this.submiterrorMessage); 
        }
      })  
      .catch(error => {  
        if (error.response) {  
          // 后端返回的错误信息
          this.submitErrorMessage = error.response.data;   
        }
        else {
          this.submitErrorMessage = '表单提交失败，请稍后再试';
        }
      });  
    },   
    goBack(){
      this.$router.push('/form-fill');
    }, 
    }, 
    
    created() {  
      this.getItems(); 
      this.content = Array(this.item.length).fill(''); 
    },

  };  
  </script>

  <style>
  .form-detail{
    margin: auto;
  }
</style>
