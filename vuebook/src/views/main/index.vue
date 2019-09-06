<style scoped>
.main {
  height: 100%;
}
.ivu-layout.ivu-layout-has-sider {
  height: 100%;
}
.ivu-layout-sider {
  min-width: 240px !important;
}
.ivu-divider,
.ivu-divider-vertical {
  margin: 0 0;
}
.ivu-layout-content {
  background-color: #f8f8f9;
}
.ivu-layout-header {
  background-color: #f7f7f7;
}
.ivu-breadcrumb {
  background-color: #e8eaec;
}
.avatar {
  float: right;
  padding: 12px 0px 0px 0px;
}
.title{
  padding: 0 0  0 33%;
  height: 70px;
}
.tag{
      background-color: #dadede;
}


</style>
<style lang="less" scoped>
/deep/.ivu-badge-count{
  margin: 15px 0px 0px 0px;
}
</style>
<template>
  <div class="main">
    <Layout>
      <Sider hide-trigger >
        <div class="logo"><img src=@/assets/img/logo.png/></div>
        <Menu :theme="theme2" @on-select="handleAdd">

          <Submenu v-for="(item, itemIndex) in menuList" :key="itemIndex" :name='item.meta.title'>
            <template slot="title">
              <Icon :type='item.meta.icon'  />{{item.meta.title}}
            </template>
            <template v-if="item.children!=null">
    
            <MenuItem  v-for="(children,index) in item.children"
             :key="index" :path="children.meta.title" :name="`${item.path}/${children.path}`" 
            > <Icon :type='children.meta.icon'/>
            {{children.meta.title}}</MenuItem>

            </template>
            
          </Submenu>
          
        </Menu>
      </Sider>
      <Divider type="vertical" />
      <Layout>
        <Header>
          <img  class="title" src=@/assets/img/title.png/>
          <span class="avatar">
            <Badge  :count="1">
              <Avatar shape="square" icon="ios-person"  src="https://i.loli.net/2017/08/21/599a521472424.jpg"/>
            </Badge>
              <Dropdown @on-click="signout">
                    <Icon class='down'  type="md-arrow-dropdown"></Icon>
                <DropdownMenu slot="list">
                    <DropdownItem>退出登陆</DropdownItem>
                </DropdownMenu>
              </Dropdown>
          </span>
        </Header>

        <div class='tag'>
          <Tag type="dot" checkable	 :color="'/home'==routerpath.path ? 'primary' : 'default'"  >首页</Tag>
          <Tag type="dot"  v-for="item in tabList"  @click.native="routerto(item.path)" checkable fade closable  :key="item.path" :name="item.name"
            @on-close="handleClose(item.name,item.path)"
            :color="item.path==routerpath.path ? 'primary' : 'default'"
            >{{ item.name}}</Tag>
        </div>
        <Content>
          <router-view/>
        </Content>
      </Layout>
    </Layout>
  </div>
</template>
<script>
import "./main.less";
import {mapState,mapGetters,mapMutations,mapActions} from 'vuex'
import localstorages from '@/libs/localstorage.js'
export default {
  data() {
    return {
      theme2: "dark",
      count: [],
    };
  },
  watch: {
    routerpath: function () {
      var value={};
      value.name=this.routerpath.meta.title
      value.path=this.routerpath.path
      debugger
      let arr=this.tabList
      //判断state中是否中是否存在这个对象
      if (value.path!='/home') {
        if(JSON.stringify(arr).indexOf(JSON.stringify(value))==-1){
        this.setTabList(value);
        localstorages.set('tabList',this.tabList);
        }
      }
    },
  },
  computed:{
    //将vue中的menulist和tabs映射出来
    ...mapState({
      menuList:state=>{return state.app.menuList},
      tabList:state=>{return state.app.tabList}
    }),
    //返回的是当前路由的meta属性的title
    routerpath() {
      return this.$route;
    },
  },
  methods:{
    ...mapGetters(['getmenuList']),
    ...mapMutations(['setTabList','removeTabList','clearTabList']),
    ...mapActions(['updateTabList']),
    routerpush(path){
      debugger
       this.$router.push({
            name: path
        })
    },
    handleAdd (name) {
      if (this.$route.path!=name) {
          this.$router.push(name)
          .catch(function(err){
              console.log(err)
          })
      }
    },
    handleClose (name,path) {
      let pp=this.tabList
      let index=pp.findIndex(item => item.name ===name)
      this.removeTabList(index)
      debugger
      if(index==0){
        this.$router.push('/home')
      }else if (index==pp.length) {
        debugger
         let oo= pp[index-1]
         this.$router.push(oo.path)
      }
    },
    signout(){
     localstorages.clear()
     this.clearTabList()
     this.$router.push({ name: 'login' })
     debugger
    },
    routerto(path){
      this.$router.push(path)
    }
  }
}
</script>