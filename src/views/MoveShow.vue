<!--  -->
<template>
    <div>
        <router-view></router-view>
        <van-tabbar v-if="showTabBar" route>
            <van-tabbar-item replace icon="home-o" to="/moveshow/index">首页</van-tabbar-item>
            <van-tabbar-item replace icon="cluster" to="/moveshow/category">分类</van-tabbar-item>
            <van-tabbar-item replace icon="cart-o" to="/moveshow/cart">购物车</van-tabbar-item>
            <van-tabbar-item replace icon="setting-o" to="/moveshow/mine">个人中心</van-tabbar-item>
        </van-tabbar>
    </div>
</template>

<script setup lang='ts'>
import { provide, ref } from 'vue';

const showTabBar = ref(true)

const toggleTabBar = (show: boolean) => {
    showTabBar.value = show
}

provide('toggleTabBar',toggleTabBar)
</script>

<style scoped>

</style>
