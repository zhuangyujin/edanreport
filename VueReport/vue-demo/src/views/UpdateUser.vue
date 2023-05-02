<template>
    <el-form :model="form" label-width="120px">
            <el-form-item label="用户id">
                <el-input v-model="form.userId" ></el-input>
            </el-form-item>
            <el-form-item label="用户名">
                <el-input v-model="form.userName" />
            </el-form-item>
            <el-form-item label="用户密码">
                <el-input v-model="form.userpassword" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">提交</el-button>
                <el-button type="primary" @click="onDelete">删除</el-button>
            </el-form-item>
    </el-form>
</template>

<script>
import {mapState } from 'vuex'
import api from '../utils/index'
export default {
    data(){
        return {
        form:{
              userId:'',
              userName:'',
              userpassword:''
            }
          ,
        }
    },computed: {
        //获取全局token
      ...mapState(["token"])
    },
    //生命周期创建后获取用户初始值进行赋值
    created(){
        console.log("渲染")
        api.read(this.token).then(res=>{
            if(res.data.code === 1) {
            console.log('赋值')
              this.form.userId =res.data.data.userId
              this.form.userName = res.data.data.userName
              this.form.userpassword = res.data.data.userpassword
            }
          })
    },
    methods:{
        onSubmit(){
            var data = {
                userName:this.form.userName,
                userId:this.form.userId,
                userpassword:this.form.userpassword,
                token:this.token
            }
            console.log(data);
            api.update(data).then(res =>{
                if(res.data.code==0)
                    alert(res.data.error)
                else
                    alert(res.data.data)    
            })
        },
         onDelete(){
     ElMessageBox.confirm(
    '您确定注销账号吗',
    '注销账号',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
        // 发请求
        var data = {
                userId:this.form.userId,
                token:this.token
            }
        api.delete(data).then(res=>{
            if(res.data.code==1){
                 ElMessage({
                type: 'success',
                message: '注销成功',
                })
            }else{
                ElMessage({
                type: 'warning',
                message: '注销失败',
                })
            }
        })
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '取消注销',
      })
    })
}
    }
}
</script>

<style>

</style>