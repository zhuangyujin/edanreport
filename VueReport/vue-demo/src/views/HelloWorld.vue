<template>
  <div class="hello">
    <p>注册用户并登录</p>
    <el-input v-model="inputname" placeholder="请输入用户名" />
    <el-input v-model="inputps" type="password" placeholder="请输入密码" show-password />
    <el-button type="primary" @click="registeredUsers">提交</el-button>
  </div>
</template>

<script>
import { ref } from 'vue'

export default {
  name: 'HelloWorld',
  setup() {
    const inputname = ref('')
    const inputps = ref('')
    return {
      inputname, inputps
    }
  },
  methods: {
    registeredUsers() {
      if (!this.inputname && !this.inputps) {
        alert('请输入用户名和密码')
      }
      else {
        //调用post请求发送用户名密码
        this.$axios.post('http://localhost:8090/user/register', {
          userName: this.inputname,
          userpassword: this.inputps
        }).then(res => {
          if(res.data.code === 1) {
            //res.data.data.token
            alert('注册成功')
            //vuex存入token
            console.log(res.data.data.token);
            this.$store.state.token = res.data.data.token;
            localStorage.setItem("token",res.data.data.token)
            this.$router.push('/find')
          }
          else{
            alert(res.data.error)
          }
        })

      }
    }
  }

}
</script>


<style scoped></style>
