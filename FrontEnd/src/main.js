import { createApp } from 'vue';  
import App from './App.vue';  
import router from './router';  
import store from './store';  
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
  
const app = createApp(App);  
  
// 使用路由  
app.use(router);  
  
// 使用 Vuex store  
app.use(store);  
  
// 挂载应用  
app.mount('#app');

app.use(ElementPlus)