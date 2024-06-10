<template>
  <div class="form-view-detail"> 
    <NavBar /> 

    <div class="welcome">  
        <img src='@/assets/welcome.png' style="width: 500px; height: 80px;"  />  
        </div>

    <form v-if="this.$store.state.item"  class="form-table">    
        <table>    
            <!-- 循环渲染已选择的表单项为表格形式 -->  
            <tr v-for="(itemName, index) in this.$store.state.item" :key="index">    
                <td>    
                  <label>{{ itemName }}</label> 
                </td>
                <td>
                  <label>{{ this.$store.state.content[index] }}</label> 
                </td>        
            </tr>    
        </table>    
    </form> 
    <div>
      <button v-if="changeable" type="view" class="view-btn" @click="goToChange()">修 改</button>
    </div> 

    <div class="pageination" v-if="sum">
      <div @click="pageUp(0)" class="pagination_page">首页</div>
      <div @click="pageUp(1)" class="pagination_page">上一页</div>
      <div class="pagination_page" 
          v-for="(i,index) in nums" :key="index" @click="jump(i)">
        {{i}}</div>
      <div @click="pageDown(1)" class="pagination_page" >下一页</div>
      <div @click="pageDown(0)" class="pagination_page pagination_page_right" >尾页</div>
    </div>

  </div>
</template>

<script>

import NavBar from './NavBar.vue'; 
import axios from 'axios';
export default {
  props: ['title'],
  data() {  
    return {  
      sum:[],//id列表
      num: [],//总条目数
      nums: [],
      curNum: 1,//当前页数默认1

      getcErrorMessage:'',
      getsErrorMessage:'', 
      changeable:false,
    };  
  }, 
  components: {  
    NavBar,  
  } ,
  methods:{
    goToChange() {  
      this.$router.push({ name: 'FormChange', params: { id: this.sum[this.curNum - 1],title:this.title} });  
    } ,
    pageUp(state){
      if (this.curNum - 1 != 0 && state == 1) {
        this.jump(this.curNum - 1);
      } else {
        this.jump(1);
      }
    },//上一页跟首页 state=1是上一页 state=0是首页
    pageDown(state){
      if (this.curNum + 1 != this.sum && state == 1) {
        this.jump(this.curNum + 1);
      } else {
        this.jump(this.sum);
      }
    },// state=1是下一页 state=0是尾页
    jump(num){
        this.curNum = num;
        this.pagers();
        this.getContent(this.sum[num]);
      },//跳转页码
    pagers(){
        //重置
        this.nums = [];
        //开始页码1
        let start = this.curNum - 3 > 1 ? this.curNum - 3 : 1;
        //当前页码减去开始页码得到差
        let interval = this.curNum - start;
        //最多7个页码，总页码减去interval 得到end要显示的数量+
        let end = (7 - interval) < this.sum.length ? (7 - interval) : this.sum.length;
        //最末页码减开始页码小于8
        if ((end - start) != 6) {
          //最末页码加上与不足7页的数量，数量不得大于总页数
          end = end + (6 - (end - start)) < this.sum.length ? end + (6 - (end - start)) : this.sum.length;
          //最末页码加上但是还不够7页，进行开始页码追加，开始页码不得小于1
          if ((end - start) != 6) {
            start = (end - 6) > 1 ? (end - 6) : 1;
          }
        }
        for (let i = start; i <= end; i++) {
          this.nums.push(i);
        }
      },//计算分页显示的数字
  getSum(){
  axios.post('http://localhost:8080/viewform/getSum', {  
  formatname:this.title
  })  
  .then(response => {  
    if (response.data.success) {  
      this.sum=response.data.titles;
      this.pagers();
      this.getContent(this.sum[0]);
    }
    else {  
      this.getsErrorMessage = response.data.message || '表单获取失败，请稍后再试';  
    }
   })  
  .catch(error => {  
    if (error.response) {  
      // 后端返回的错误信息
      this.getsErrorMessage = error.response.data;   
    }
    else {
      this.getsErrorMessage = '表单获取失败，请稍后再试';
    }
  }); 
},
getContent(id){
  //console.log(id),
  this.$store.commit('SET_ITEM',[]),
  this.$store.commit('SET_CONTENT', []),
  this.changeable=false
  axios.post('http://localhost:8080/viewform/getContent', {  
    num:id,
    title:this.title,
  })  
  .then(response => {  
    if (response.data.success) {  
      //console.log("success"),
      this.$store.commit('SET_ITEM', response.data.item),
      this.$store.commit('SET_CONTENT', response.data.itemValue),
      this.changeable=response.data.changeable,
      console.log(this.changeable)
    }
    else {  
      this.getcErrorMessage = response.data.message || '表单获取失败，请稍后再试';  
      alert(response.data.message);
    }
   })  
  .catch(error => {  
    if (error.response) {  
      // 后端返回的错误信息
      this.getcErrorMessage = error.response.data;   
    }
    else {
      this.getcErrorMessage = '表单获取失败，请稍后再试';
    }
  }); 
}
  },
  created() {  
    this.getSum();
  }, 
};
</script>

<style scoped>
.view-btn {  
  margin-left: 160px; /* 左边距 */  
  padding: 6px 10px; /* 内边距 */  
  background-color: #6292ff; /* 蓝色背景 */  
  color: #fff; /* 白色文本 */  
  border: none; /* 无边框 */  
  border-radius: 12px; /* 圆角 */  
  cursor: pointer; /* 鼠标悬停时变为小手图标 */  
  transition: background-color 0.3s ease; /* 背景色过渡效果 */  
  margin-top: 7px;
  font-size: 15px;
  font-family: "黑体";
}  
  
/* 填写按钮点击后的样式（可选） */  
.view-btn:active {  
  background-color: #0056b3; /* 深蓝色背景 */  
}
.form-view-detail{  
max-width: 400px;
margin: auto;
}
.pageination {
  margin-top: 20px;
  color: #48576a;
  font-size: 12px;
  display: inline-block;
  user-select: none;
}

.pagination_page {
  padding: 0 4px;
  border: 1px solid #d1dbe5;
  border-right: 0;
  background: #fff;
  font-size: 13px;
  min-width: 28px;
  height: 28px;
  line-height: 28px;
  cursor: pointer;
  box-sizing: border-box;
  text-align: center;
  float: left;
}

.pagination_page_right {
  border-right: 1px solid #d1dbe5;
}

.form-table table {  
  width: 100%; /* 表格宽度 */  
  border-collapse: collapse; /* 合并边框 */  
}  
  
.form-table table tr td {  
  padding: 10px; /* 单元格内边距 */  
  border: 1px solid #ddd; /* 单元格边框 */  
}  
  
.form-table table tr td:first-child {  
  width: 30%; /* 可以调整标签列的宽度 */  
}  

.form-table table tr td input {  
  width: 100%; /* 输入字段占满单元格宽度 */  
  box-sizing: border-box; /* 包含内边距和边框 */  
}  
</style>
