<template>  
    <div>
      <NavBar/>
    </div>
    <div class="form-detail">  

      <div class="welcome">  
        <img src='@/assets/welcome.png' style="width: 500px; height: 80px;"  />  
        </div>

      <p>{{ title }}</p>  

      <div v-for="(itemName, index) in item" :key="index" class="form-group">  
        <label class="label">{{ itemName }}</label>  
        <div style="margin: 5px 0" />
        <el-input
        v-model="content[index]"
        style="width: 400px"
        autosize
        @focus="keyJudge(item[index],content[index],index)"
        @blur="judge(item[index],content[index],index)"
        
        />

        <div style="margin: 15px 0" />
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
      keys:["姓名", "名字","作者姓名","作者","作者名","论文标题", "标题","论文名称","名称","成果名称","获奖名称","项目名称","课程名称","论文","专著","专利","获奖","专著名称","专利名称","获奖名称"],
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
      axios.post('http://localhost:8080/match', {  
        item:item,
        itemValue:itemValue
        })  
        .then(response => {  
          if (response.data.success) {  
            this.content[index]=response.data.ans
          }
         })
    }, 
    keyMatch(item, itemValue,index) {    
        console.log(`keymatch for item: ${item}, value: ${itemValue}`);
      axios.post('http://localhost:8080/key-match', {  
        item:item,
        itemValue:itemValue,
        title:this.title,
        username:this.$store.state.username,
        }) 
        .then(response => {  
          if (!response.data.success) {  
            this.content[index]=this.$store.state.username;
            alert("该表只能填写本人相关信息");
          }
         })
    },
    judge(item,itemValue,index){
      if(this.isKey){
        this.keyMatch(item,itemValue,index);
        this.isKey=false;
      }
      else{
        this.Match(item,itemValue,index);
      }
    },
    keyJudge(item,itemValue,index){  
      console.log(item);
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
    max-width: 400px;
    margin: auto;
  }
  .container {  
    display: flex;  
    flex-direction: row;  
  }  
  .lable{
    margin-bottom: 10px;
  }
  .submit-btn,.return-btn {  
  margin-left: 70px; /* 左边距 */  
  padding: 8px 15px; /* 内边距 */  
  background-color: #6292ff; /* 蓝色背景 */  
  color: #fff; /* 白色文本 */  
  border: none; /* 无边框 */  
  border-radius: 8px; /* 圆角 */  
  cursor: pointer; /* 鼠标悬停时变为小手图标 */  
  transition: background-color 0.3s ease; /* 背景色过渡效果 */  
  margin-top: 7px;
}  
  


</style>
