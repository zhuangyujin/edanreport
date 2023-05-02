<template>
    <div class="hello">
      <p>查看个人</p>
      <el-table :data="user" style="width: 100%">
        <el-table-column prop="userId" label="用户id" width="400"   />
        <el-table-column prop="userName" label="用户名" width="400" />
        <el-table-column prop="userpassword" label="密码" />

      </el-table>
      <el-button type="primary" @click="checkUserInfo">查看个人</el-button>



    </div>


</template>

<script>
import {mapState } from 'vuex'
import api from '../utils/index'
export default {
    data(){
        return {
          user:[
            {
              userId:'',
              userName:'',
              userpassword:''
            }
          ],
            
          
        }
    },
    computed: {
      ...mapState(["token"])
    },
    methods:{
      checkUserInfo() {
        //将全局vuex的token传进来
        api.read(this.token).then(
          res=>{
            if(res.data.code === 0) {
              alert(res.data.error)
            }else {
              this.user[0] = res.data.data
            }
          }
        )

        // this.$axios.get('http://localhost:8090/user/read',{
        //   headers:{
        //     'Token':this.token
        //   }
        // }).then(res=>{
        //     if(res.data.code === 0) {
        //       alert(res.data.error)
        //     }else {
        //       this.user[0] = res.data.data
        //     }
        //   })
      },
      update() {
        console.log(123);
      }
    }
}
</script>

<style>

</style>