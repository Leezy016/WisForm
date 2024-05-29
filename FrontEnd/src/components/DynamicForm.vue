<template>
  <div class="dynamic-form">
    <h2>选择表单项</h2>
    <div v-for="(field, index) in selectedFields" :key="index" class="form-item">
      <input type="text" v-model="field.label" placeholder="表单项名称" class="field-input">
      <select v-model="field.type" class="field-select">
        <option value="text">文本</option>
        <option value="number">数字</option>
        <!-- 可以根据需要添加更多的选项 -->
      </select>
      <button @click="removeField(index)" class="remove-btn">-</button>
    </div>
    <button @click="addField" class="add-btn">添加表单项</button>
    <button @click="generateForm" class="generate-btn">确认</button>
    <form v-if="showForm" @submit.prevent="submitForm" class="form">
      <div v-for="(field, index) in selectedFields" :key="index" class="form-field">
        <label :for="field.id" class="field-label">{{ field.label }}：</label>
        <input :type="field.type" :id="field.id" v-model="formData[field.id]" class="field-input">
      </div>
      <button type="submit" class="submit-btn">提交</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      selectedFields: [{ id: 'field1', label: '字段1', type: 'text' }],
      showForm: false,
      formData: {}
    };
  },
  methods: {
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
      }, {});
    },
    submitForm() {
      // 处理表单提交逻辑
      console.log('提交表单:', this.formData);
    }
  }
};
</script>

<style scoped>
.dynamic-form {
  max-width: 400px;
  margin: auto;
}

.dynamic-form h2 {
  margin-bottom: 20px;
}

.form-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
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

.add-btn, .generate-btn {
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

.form-field {
  margin-bottom: 10px;
}

.field-label {
  margin-right: 10px;
}

.submit-btn {
  display: block;
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>
