<template>
  <div class="form-fill">
      <NavBar/>
  </div>
</template>

<script>
  import NavBar from '../components/NavBar.vue';
  export default {
      components: {
          NavBar,
      }
  };
</script>

<style>  
.container {  
  display: flex;  
  flex-direction: row;  
}  
  
.content {  
  margin-left: 200px; /* 与侧边栏的宽度相同，以确保内容不会与侧边栏重叠 */  
  /* 其他样式... */  
}  
</style>