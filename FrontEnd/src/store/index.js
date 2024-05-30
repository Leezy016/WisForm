import { createStore } from 'vuex' // 引入 Vuex 的 createStore 函数  
  
// 创建 Vuex 的 store 实例  
const store = createStore({  
  state:{ 
      permissions:[],
      department:[],
      role:''
  },  
  getters: {  

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
    } 
  },  
  actions: {  
  
  },  
  modules: {  
    
  }  
})  
  
export default store;