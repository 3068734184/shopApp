<!--  -->
<template>
    <div>
        <van-tabbar v-model="active">
            <van-tabbar-item icon="home-o">首页</van-tabbar-item>
            <van-tabbar-item icon="cluster">分类</van-tabbar-item>
            <van-tabbar-item icon="cart-o">购物车</van-tabbar-item>
            <van-tabbar-item icon="setting-o" :to="({ name: 'home' })">个人中心</van-tabbar-item>
        </van-tabbar>
    </div>
</template>

<script setup lang='ts'>
import { ref } from 'vue'
const active = ref('')
</script>

<style scoped></style>
