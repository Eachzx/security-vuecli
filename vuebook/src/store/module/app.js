const app= {
    state: {
      menuList:[],
      tabList:[],
      },
    getters: {
        getmenuList:(state)=>{
          return state.menuList;
        }
      },
    mutations: {
      //设置menuList
      setmenuList(state,menuList){     
          state.menuList=menuList;
      },
      //设置tabList
      setTabList(state,tabList){   
        state.tabList.push(tabList);
      },
      removeTabList(state,index){
        state.tabList.splice(index, 1)
      },
      clearTabList(state){
        state.tabList.splice(0,state.tabList.length)
      }
      
    },
    actions: {
      updateTabList({ commit, state }, tabs){
        debugger
        for(var i=0;i<tabs.length;i++){
          if(!state.tabList.includes(tabs[i]))
            commit('setTabList',tabs[i])
        }
      }
    }
}
export default app;