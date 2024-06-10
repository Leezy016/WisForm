### 创建表单功能
页面描述：
png

### 问题及解决
问题一：
form-fill页面刷新后侧栏无法显示
当调用SET_PERMISSIONS mutation 来设置permissions的值后，这个值会在当前Vuex store的上下文中被更新。然而，当刷新页面时，Vuex store的状态会重置为其初始状态，这是因为在Vue.js中，Vuex store的状态是保存在内存中的，并且不会持久化到浏览器的本地存储或其他任何地方。当页面刷新时，Vue实例、组件状态以及Vuex store都会被重新创建，并且使用其初始状态值。
解决:将Vuex store的状态持久化。
使用vuex-persistedstate插件，将Vuex store的状态自动保存到浏览器的localStorage或sessionStorage中，并在页面加载时从中恢复。
``` bash
npm install --save vuex-persistedstate
```
/store/index.js:
```javascript
import { createStore } from 'vuex'  
import createPersistedState from 'vuex-persistedstate'  
  
const store = createStore({  
  // ...您的store配置  
  plugins: [  
    createPersistedState({  
      // 使用localStorage作为默认存储引擎  
      storage: window.localStorage,  
      // 选择要持久化的状态字段  
      reducer(val) {  
        return {  
          permissions: val.permissions,  
          // 可以添加其他需要持久化的字段  
        }  
      }  
    })  
  ]  
})  
export default store
```
