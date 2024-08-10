<<<<<<< HEAD
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
          <van-row v-if="activeIndex === loopIndex" :gutter="[5, 15]">
            <van-col span="8" v-for="subItem in item.subItems" :key="subItem.id">
              <div class="ca-content">
                <div class="ca-content-img">
                  <img :src="subItem.imgUrl" alt="" class="trao-image">
                </div>
                <div class="ca-content-name">{{ subItem.name }}</div>
              </div>
            </van-col>
          </van-row>
        </div>
      </template>
    </van-tree-select>
  </div>
</template>

<script setup lang='ts'>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

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


const items = [
  {
    id: 1,
    text: '食品',
    subItems: [
      { id: 1, name: '糖果', imgUrl: 'https://m.360buyimg.com/babel/jfs/t1/215072/20/30644/9019/646da7a1F63613a8c/a5b2428860e14917.jpg' },
      { id: 2, name: '糖果', imgUrl: 'https://m.360buyimg.com/babel/jfs/t1/215072/20/30644/9019/646da7a1F63613a8c/a5b2428860e14917.jpg' },
      { id: 3, name: '糖果', imgUrl: 'https://m.360buyimg.com/babel/jfs/t1/215072/20/30644/9019/646da7a1F63613a8c/a5b2428860e14917.jpg' },
      { id: 4, name: '糖果', imgUrl: 'https://m.360buyimg.com/babel/jfs/t1/215072/20/30644/9019/646da7a1F63613a8c/a5b2428860e14917.jpg' },
      { id: 5, name: '糖果', imgUrl: 'https://m.360buyimg.com/babel/jfs/t1/215072/20/30644/9019/646da7a1F63613a8c/a5b2428860e14917.jpg' },
      { id: 6, name: '糖果', imgUrl: 'https://m.360buyimg.com/babel/jfs/t1/215072/20/30644/9019/646da7a1F63613a8c/a5b2428860e14917.jpg' },
      // 其他食品项
    ]
  },
  {
    id: 2,
    text: '电器',
    subItems: [
      { id: 1, name: '炒菜机器人', imgUrl: 'https://m.360buyimg.com/babel/jfs/t1/92370/3/40455/5787/646dd448Fb4a0ed9f/6d95d4dab252795f.jpg' },
      { id: 2, name: '炒菜机器人', imgUrl: 'https://m.360buyimg.com/babel/jfs/t1/92370/3/40455/5787/646dd448Fb4a0ed9f/6d95d4dab252795f.jpg' },
      { id: 3, name: '炒菜机器人', imgUrl: 'https://m.360buyimg.com/babel/jfs/t1/92370/3/40455/5787/646dd448Fb4a0ed9f/6d95d4dab252795f.jpg' },
      { id: 4, name: '炒菜机器人', imgUrl: 'https://m.360buyimg.com/babel/jfs/t1/92370/3/40455/5787/646dd448Fb4a0ed9f/6d95d4dab252795f.jpg' },
      { id: 5, name: '炒菜机器人', imgUrl: 'https://m.360buyimg.com/babel/jfs/t1/92370/3/40455/5787/646dd448Fb4a0ed9f/6d95d4dab252795f.jpg' },
      { id: 6, name: '炒菜机器人', imgUrl: 'https://m.360buyimg.com/babel/jfs/t1/92370/3/40455/5787/646dd448Fb4a0ed9f/6d95d4dab252795f.jpg' },
      { id: 7, name: '炒菜机器人', imgUrl: 'https://m.360buyimg.com/babel/jfs/t1/92370/3/40455/5787/646dd448Fb4a0ed9f/6d95d4dab252795f.jpg' },
      { id: 8, name: '炒菜机器人', imgUrl: 'https://m.360buyimg.com/babel/jfs/t1/92370/3/40455/5787/646dd448Fb4a0ed9f/6d95d4dab252795f.jpg' },
      { id: 9, name: '炒菜机器人', imgUrl: 'https://m.360buyimg.com/babel/jfs/t1/92370/3/40455/5787/646dd448Fb4a0ed9f/6d95d4dab252795f.jpg' },
      // 其他电器项
    ]
  }
]
</script>

<style scoped>
.ca-content-img {
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
=======
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
>>>>>>> 67571dec71b6ab3fc294f8acffcbe2f29a9c63cf
