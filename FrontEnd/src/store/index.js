import { createStore } from 'vuex' // 引入 Vuex 的 createStore 函数  
import createPersistedState from 'vuex-persistedstate'

// 创建 Vuex 的 store 实例  
const store = createStore({  
  state(){ 
      return{
      permissions:[1,3],
      department:[],
      role:'',
      username:''
      }
  },  
  getters: {  
    username: state => state.username
  },  
  mutations: {  
    SET_PERMISSIONS(state, permissions) {  
      state.permissions = permissions;  
    } , 
    SET_DEPARTMENT(state, department) {  
      state.department = department;  
    } ,
    SET_ROLE(state, role) {  
      state.role = role;  
    } ,
    SET_USERNAME(state, username) {  
      state.username = username;  
    }
  },  
  actions: {  
  },  
  modules: {   
  } ,
  plugins: [  
    createPersistedState({  
      // 使用localStorage作为默认存储引擎  
      storage: window.localStorage,  
      // 选择要持久化的状态字段  
      reducer(val) {  
        return {  
          permissions: val.permissions, 
          department:val.department,
          role:val.role,
          username:val.username
          // 可以添加其他需要持久化的字段  
        }  
      }  
    })  
  ]  
})  
  
export default store;