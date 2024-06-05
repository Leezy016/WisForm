<template>
  <div> 
    <NavBar/> <!-- 引入并使用NavBar组件 --> 
  </div>

  <div class="dynamic-form">  
    <!-- 动态表单标题输入 -->  
    <div class="form-title-input">    
        <label for="formTitle">表单标题：</label>    
        <input type="text" id="formTitle" v-model="formTitle" placeholder="请输入表单标题">  <!-- 绑定到Vue实例的formTitle属性 -->  
    </div>

    <!-- <div>
      <h4>基于Vue.2X的日期选择器</h4>
        <div style="width: 502px;">
          <DatePicker v-on:picked="picked"></DatePicker>
        </div>
    </div> -->

    <div>  
    <label for="myCheckbox">  
      <input type="checkbox" id="myCheckbox" v-model="only" />  
      填表人只能填写自己的信息  
    </label>  
  </div> 

  <div>  
    <p>请选择可以填写表单的用户类型</p>
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
  
    <!-- 循环渲染已选择的表单项 -->  
    <div v-for="(field, index) in selectedFields" :key="index" class="form-item">  
        <input type="text" v-model="field.label" placeholder="表单项名称" class="field-input"> <!-- 表单项名称输入框 -->  
        <select v-model="field.type" class="field-select">  
            <option value="text">文本</option> <!-- 表单项类型为文本 -->  
            <option value="number">数字</option> <!-- 表单项类型为数字 -->  
            <!-- 更多的选项 -->  
        </select>  
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
</template>

  <script>
  import NavBar from './NavBar.vue'; 
  import { mapGetters } from 'vuex';
  import axios from 'axios';
  import router from '../router';
  import dayjs from 'dayjs';
  //import DatePicker from './DatePicker.vue';
  
  export default {
    name: 'FormCreate',
    components: {  
      NavBar
      //DatePicker
    } ,
    data() {
      return {
        selectedFields: [{ id: 'field1', label: '字段1', type: 'text' }],
        showForm: false,
        formTitle:'',
        only: 0 ,// 默认不选中 
        roleList:[0,0,0],
        ddl:"",//2024-06-11
        minDate: dayjs(new Date()),
        maxDate: dayjs(new Date()).add(20, 'day'),
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
      ])     
    },
    methods: {
      picked(year, month, date) {
				console.warn(`你选择了${year}年${month}月${date}日`)
			},
      addField() {
        const id = `field${this.selectedFields.length + 1}`;
        this.selectedFields.push({ id, label: `字段${this.selectedFields.length + 1}`, type: 'text' });
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
        const { username } = this; 
        console.log(this.only);
        console.log(this.ddl);
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
            this.selectedFields=[{ id: 'field1', label: '字段1', type: 'text' }],
            this.showForm=false,
            this.formTitle='' 
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
  background-color: #007bff;
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