<style scoped>
.login{
    position: relative;
    width: 100%;
    height: 100%;
    background-image:url('../../assets/img/login-bg.jpg') ;
}
.ivu-card-bordered{
    position: relative;
    left: 70%;
    top: 30%;
}
</style>
<template>
  <div class="login">
   <Card style="width:350px">
        <p slot="title">
            <Icon type="md-ribbon" />
            登陆
        </p>
      <Form ref="loginForm" :model="form" :rules="rules" @keydown.enter.native="handleSubmit">
    <FormItem prop="userName">
      <Input v-model="form.userName" placeholder="请输入用户名">
        <span slot="prepend">
          <Icon :size="16" type="ios-person"></Icon>
        </span>
      </Input>
    </FormItem>
    <FormItem prop="password">
      <Input type="password" v-model="form.password" placeholder="请输入密码">
        <span slot="prepend">
          <Icon :size="14" type="md-lock"></Icon>
        </span>
      </Input>
    </FormItem>
    <FormItem>
      <Button @click="handleSubmit" type="primary" long>登录</Button>
    </FormItem>
  </Form>
    </Card>
    </div>
</template>

<script>
import {login} from '@/api/app.js';
import router from '@/router/index'
import store from '@/store/store';
import { mapActions, mapMutations,mapState} from 'vuex'
import {formatRoutes} from '@/libs/utils.js'
import localstorages from '@/libs/localstorage.js'

export default {
  name: 'LoginForm',
  props: {
    userNameRules: {
      type: Array,
      default: () => {
        return [
          { required: true, message: '账号不能为空', trigger: 'blur' }
        ]
      }
    },
    passwordRules: {
      type: Array,
      default: () => {
        return [
          { required: true, message: '密码不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  data () {
    
    return {
      form: {
        userName: 'libai',
        password: ''
      }
    }
  },
  computed: {
    ...mapState({
      name:state=>{return state.app.name},
      menuList:state=>{return state.app.menuList}
    }),
    rules () {
      return {
        userName: this.userNameRules,
        password: this.passwordRules
      }
    }

  },
  methods: {
    ...mapMutations(['setmenuList']),
    handleSubmit () {
      login(this.form).then(res=>{
      debugger
      localstorages.set('menuList',res.data.routers);
      localstorages.set('token',res.data.token)
      debugger
      this.$router.push({
            name: 'home'
          })
      }).catch(() => console.log('promise catch err')); //捕获异常
    },
  }
}
</script>
