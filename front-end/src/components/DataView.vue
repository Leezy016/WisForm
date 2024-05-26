<template>
  <div class="data-view">
    <h2>数据查看</h2>
    <input type="text" v-model="searchQuery" placeholder="搜索关键字">
    <button @click="search">搜索</button>
    <button @click="reset">重置</button>
    
    <table>
      <thead>
        <tr>
          <th>字段1</th>
          <th>字段2</th>
          <!-- 其他字段 -->
        </tr>
      </thead>
      <tbody>
        <tr v-for="(data, index) in filteredData" :key="index">
          <td>{{ data.field1 }}</td>
          <td>{{ data.field2 }}</td>
          <!-- 其他字段 -->
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      searchData: [], // 存储从后端获取的填报数据
      searchQuery: '', // 搜索关键字
      filteredData: [] // 搜索和过滤后的数据
    };
  },
  mounted() {
    // 模拟从后端获取填报数据
    this.searchData = [
      { field1: '数据1', field2: '数据2' },
      // 添加更多数据
    ];
    this.filteredData = this.searchData;
  },
  methods: {
    search() {
      // 根据搜索关键字过滤数据
      this.filteredData = this.searchData.filter(item => {
        // 根据实际情况定义搜索逻辑
        return item.field1.includes(this.searchQuery) || item.field2.includes(this.searchQuery);
      });
    },
    reset() {
      // 重置搜索条件，显示所有数据
      this.searchQuery = '';
      this.filteredData = this.searchData;
    }
  }
};
</script>

<style scoped>
/* 样式 */
</style>
