<!-- mobileCategory -->
<template>
  <div>
    <div style="display: flex; align-items: center;">
      <van-nav-bar style="display: flex; flex-grow: 1;" left-arrow @click-left="onClickLeft" />
      <van-search v-model="searchInfo" style="flex-grow: 3;" placeholder="请输入搜索关键词" @search="onSearch" />
    </div>

    <van-tree-select v-model:main-active-index="activeIndex" :items="items">
      <template #content>
        <div v-for="(item, loopIndex) in items" :key="item.id">
          <van-row style="padding-left: 10px;" v-if="activeIndex === loopIndex" :gutter="[5, 15]">
            <van-col span="8" v-for="subItem in item.subItems" :key="subItem.id">
              <div class="ca-content">
                <div class="ca-content-img">
                  <img :src="subItem.imgUrl" alt="" class="trao-image">
                </div>
                <div class="ca-content-name">{{ subItem.text }}</div>
              </div>
            </van-col>
          </van-row>
        </div>
      </template>
    </van-tree-select>
  </div>
</template>

<script setup lang='ts'>
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
import { categoryApi } from '../../api';

// 返回键
const router = useRouter()
const onClickLeft = () => {
  router.push({ path: "/moveshow/index" })
}

// 搜索框
const searchInfo = ref()
const onSearch = (value: any) => {
  console.log("searchInfo=>", value);
}

// 分类选择
const activeIndex = ref(0);


const items = ref()

const getItems = () => {
  categoryApi.MSelect.call().then((res) => {
    // console.log("res=>",res);
    items.value = res.data
  })
}

onMounted(() => {
  getItems()
})
</script>

<style scoped>
.ca-content-img {
  display: flex;
  width: 3.34rem;
  height: 3.34rem;
}

.trao-image {
  max-width: 100%;
  max-height: 100%;
}

.ca-content-name {
  width: 66px;
  font-size: small;
  color: #333;
  text-align: center;
}

:global(.van-tree-select__nav) {
  flex-grow: 0.7;
}

:global(.van-tree-select__nav-item) {
  display: flex;
  justify-content: center;
}
</style>
