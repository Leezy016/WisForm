<template>
  <div class="form-create">
  <div> 
    <NavBar/> 
  </div>

  <div class="welcome">  
      <img src='@/assets/welcome.png' style="width: 500px; height: 80px;"  />  
  </div>

  <div class="text">
    <p style="color: black;">请填写创建表单需要的信息</p>
  </div>

  <div class="dynamic-form">  
    <div class="form-title-input">    
        <label>表单标题：</label>    
        <div style="margin: 5px 0" />
        <el-input
        v-model="formTitle"
        style="width: 400px"
        autosize
        type="textarea"
        placeholder="请输入表单标题"
        />  
    </div>

    <div class="demo-date-picker">
    <div class="block">
      <span class="demonstration">截止日期：</span>
      <el-date-picker
        v-model="date"
        type="date"
        placeholder="请选择截止日期"
        :size="size"
        @change="picked"
      />
    </div>
  </div>

    <div>  
      <div style="margin: 5px 0" />
    <label for="myCheckbox">  
      <input type="checkbox" id="myCheckbox" v-model="only" />  
      填表人只能填写自己的信息  
    </label>  
  </div> 

  <div style="margin: 8px 0" />
  <span class="demonstration">可以填写该表单的用户类型</span>
  <div style="margin: 5px 0" />
  <div>  
    <label for="myCheckbox">  
      <input type="checkbox" id="myCheckbox" v-model="roleList[0]" />  
      院长  
    </label> 
    <label for="myCheckbox">  
      <input type="checkbox" id="myCheckbox" v-model="roleList[1]" />  
      系主任 
    </label>  
    <label for="myCheckbox">  
      <input type="checkbox" id="myCheckbox" v-model="roleList[2]" />  
      老师 
    </label> 
  </div> 

  <div style="margin: 10px 0" />
  
    <!-- 循环渲染已选择的表单项 --> 
    <p class="demonstration">创建表单项</p> 
    <div v-for="(field, index) in selectedFields" :key="index" class="form-item">
      <el-input
        v-model="field.label"
        style="width: 400px"
        autosize
        type="textarea"
        placeholder="请输入表单项名称"
        /> 
        <button @click="removeField(index)" class="remove-btn">-</button> <!-- 移除表单项的按钮 --> 
    </div>  
  
    <!-- 添加表单项的按钮 -->  
    <button @click="addField" class="add-btn">添加表单项</button>  
  
    <!-- 确认按钮，用于生成表单 -->  
    <button @click="generateForm" class="generate-btn">确认</button>  
  
    <!-- 根据showForm的值条件渲染表单 -->  
    <form v-if="showForm" @submit.prevent="submitForm" class="form-table">    
        <table>    
            <!-- 循环渲染已选择的表单项为表格形式 -->  
            <tr v-for="(field, index) in selectedFields" :key="index">    
                <td>    
                    <label :for="field.id" class="field-label">{{ field.label }}</label>  <!-- 表单项标签 -->  
                </td>    
                <td>    
                  <p >  </p>
                </td>    
            </tr>    
        </table>    
        <button type="submit" class="submit-btn">提交</button>  <!-- 提交表单的按钮 -->  
    </form>  
  </div>
</div>
</template>

  <script>
  import NavBar from './NavBar.vue'; 
  import { mapGetters } from 'vuex';
  import axios from 'axios';
  import router from '../router';
  //import DatePicker from './DatePicker.vue';
  
  export default {
    name: 'FormCreate',
    components: {  
      NavBar
      //DatePicker
    } ,
    data() {
      return {
        selectedFields: [{ id: 'field1', label: '',type:'text' }],
        showForm: false,
        formTitle:'',
        only: 0 ,// 默认不选中 
        roleList:[0,0,0],
        ddl:'',//2024-06-11
        date:new Date(),
        errorMessage:''
      };
    }, 
    computed: {  
      // 计算属性返回label数组  
      labelArray() {  
        return this.selectedFields.map(field => field.label);  
      },  
      // 计算属性返回type数组  
      typeArray() {  
        return this.selectedFields.map(field => field.type);  
      },
      ...mapGetters([  
        'username'  
      ]) ,    
    },
    methods: {
      formattedDate() {  
      const year = this.date.getFullYear();  
      const month = String(this.date.getMonth() + 1).padStart(2, '0'); // 月份是从 0 开始的，所以需要 +1，并使用 padStart 填充 0  
      const day = String(this.date.getDate()).padStart(2, '0'); // 使用 padStart 填充 0  
      return `${year}-${month}-${day}`;  
    }, 
      picked() {
				this.ddl=this.formattedDate();
        console.log(this.ddl);
			},
      addField() {
        const id = `field${this.selectedFields.length + 1}`;
        this.selectedFields.push({ id, label: '' ,type:'text'});
      },
      removeField(index) {
        this.selectedFields.splice(index, 1);
      },
      generateForm() {
        this.showForm = true;
        // 生成表单数据对象，用于绑定表单输入
        this.formData = this.selectedFields.reduce((acc, field) => {
          acc[field.id] = '';
          return acc;
        });
      },
      submitForm() {
        if (!this.formTitle){
          this.errorMessage = '请填写表单标题'; 
          alert(this.errorMessage); 
          return;  
        } 
        if (!this.ddl){
          this.errorMessage = '请选择截止日期'; 
          alert(this.errorMessage); 
          return;  
        } 
        var sum=0;
        for(var i=0;i<this.roleList.length;i++){
          sum+=this.roleList[i];
        }
        if(!sum){
          this.errorMessage = '请选择可以填写该表的用户类型'; 
          alert(this.errorMessage); 
          return;
        }
        for (var i = 0; i < this.selectedFields.length; i++) {  
        if(!this.selectedFields[i].label ){
          this.errorMessage = '表单项名称不可以为空'; 
          alert(this.errorMessage); 
          return;  
        } 
        }
        const { username } = this; 
        axios.post('http://localhost:8080/createform', {  
          title:this.formTitle,
          Publisher: username,
          Item: this.labelArray,  
          ItemType: this.typeArray,  
          only:this.only,
          roleList:this.roleList,
          ddl:this.ddl
        })  
        .then(response => {  
          //console.log('后端返回数据：', response.data); 
          if (response.data.success) { 
            this.selectedFields=[{ id: 'field1', label: '', type: 'text' }],
            this.showForm= false,
            this.formTitle='',
            this.only= 0 ,
            this.roleList=[0,0,0],
            this.ddl="",
            this.date=new Date(),
            router.push('/form-create'); 
            alert('表单创建成功！');
          }
          else {  
            //console.log(response.data.message);
            this.errorMessage = response.data.message || '表单创建失败了，请稍后再试';  
            alert(this.errorMessage);
          }
         })  
        .catch(error => {  
          if (error.response) {  
            // 后端返回的错误信息
            //console.log(error.response.data);
            this.errorMessage = error.response.data;   
          }
          else {
            this.errorMessage = '表单创建失败，请稍后再试';
          }
        }); 
      },
    }, 
  };
  </script>

<style scoped>

.remove-btn {  
  margin-left: 160px; /* 左边距 */  
  padding: 3px 10px; /* 内边距 */  
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
.remove-btn:active {  
  background-color: #0056b3; /* 深蓝色背景 */  
}

.submit-btn,.add-btn,.generate-btn {  
  margin-left: 0px; /* 左边距 */  
  padding: 3px 10px; /* 内边距 */  
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
.submit-btn,.add-btn,.generate-btn {  
  background-color: #0056b3; /* 深蓝色背景 */  
}

.container {  
    display: flex;  
    flex-direction: row;  
  }  
    
  .form-create{  
    max-width: 400px;
    margin: auto;
  }  

.dynamic-form {
  max-width: 400px;
  margin: auto;
}

.form-title-input{
  margin-top: 10px;
  margin-bottom: 10px;
}

.form-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
  margin-top:20px;
}

.field-input {
  flex: 1;
  margin-right: 10px;
}

.field-select {
  width: 120px;
}

.remove-btn {
  margin-left: 10px;
}

.add-btn,.submit-btn , .generate-btn {
  display: block;
  width: 100%;
  padding: 10px;
  background-color: #6292ff;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-bottom: 10px;
  
}

.form {
  margin-top: 20px;
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
.demo-5{
      padding: 10px;
      background: #fff;
      .btn{
        margin-right: 20px;
      }
      .note{
        font-size: 14px;
        padding: 10px 0;
        color: red;
      }
    }
</style>