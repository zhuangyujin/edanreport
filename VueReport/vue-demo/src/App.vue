<template>
  <div id="app">
    <router-link to="/register" style="margin-right: 50px;">注册</router-link>
    <router-link to="/find" style="margin-right: 50px;">个人信息</router-link>
    <router-link to="/update">修改信息</router-link>
    <!-- 路由入口 -->
    <router-view></router-view>
    <!-- 路由跳转 -->

  </div>
</template>

<script>


export default {
  name: 'App',
}
</script>

<style></style>
