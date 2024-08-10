<!-- mobileList -->
<template>
    <div>
        <div style="display: flex; align-items: center;">
            <van-nav-bar style="display: flex; flex-grow: 1;" left-arrow @click-left="onClickLeft" />
            <van-search v-model="searchInfo" style="flex-grow: 3;" placeholder="请输入搜索关键词" @search="onSearch" />
        </div>

        <van-sticky>
            <div class="tags">
                <van-tag class="tags-item" color="#f2f2f7" text-color="#666">京东物流</van-tag>
                <van-tag class="tags-item" color="#f2f2f7" text-color="#666">新品</van-tag>
                <van-tag class="tags-item" color="#f2f2f7" text-color="#666">品牌</van-tag>
                <van-tag class="tags-item" color="#f2f2f7" text-color="#666">优选服务</van-tag>
            </div>
        </van-sticky>

        <div class="search-list" style="padding-top: 10px;">
            <van-card v-for="(item, index) in listData" :key="index" :title="item.name" :thumb="item.img">
                <template #price>
                    ￥{{ item.price }}万
                </template>
                <template #tags>
                    <van-tag plain type="primary">{{ item.brief }}</van-tag>
                </template>
            </van-card>
        </div>
    </div>
</template>

<script setup lang='ts'>
import { inject, onBeforeUnmount, onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { productApi } from '../../api';

const router = useRouter()
const route = useRoute()

const onClickLeft = () => {
    router.go(-1)
}

const toggleTabBar = inject<((show: boolean) => void)>('toggleTabBar');
if (toggleTabBar) {
    onMounted(() => {
        toggleTabBar(false)
    });
    onBeforeUnmount(() => {
        toggleTabBar(true);
    });
}

const searchInfo = ref('')

onMounted(() => {
    searchInfo.value = route.query.search as string;
    // console.log(searchInfo.value);
    searchProduct()
})

const onSearch = () => {
    searchProduct()
}

interface Response {
    token?: string; // 使用可选属性来表示token可能不存在
    message: string; // 假设message总是存在的
    item? : any;
}

const searchProduct = () => {
    productApi.select.call({
        name: searchInfo.value
    }).then((res) => {
        // console.log("res==", res);
        const data = res.data as Response;
        listData.value = data.item
    })
}

const listData = ref()

</script>

<style scoped>
:global(.van-search__content) {
    border-radius: 20px !important;
}

:global(.van-nav-bar .van-icon) {
    color: #60626c !important;
    font-size: 20px !important;
}

.tags {
    padding: 10px;
    background-color: #ffffff;
    display: flex;
    justify-content: space-around;
}

.tags-item {
    height: 25px;
    padding: 2.5px 5px;
    width: 70px;
    justify-content: center;
    border-radius: 8px;
}

.tags-item+.tags-item {
    margin-left: 5px;
}

:global(.van-card) {
    padding: 10px 0 5px 10px;
}

:global(.van-card__header) {
    height: 120px !important;
}

:global(.van-card__thumb) {
    width: inherit !important;
    height: inherit !important;
}

:global(.van-card__title) {
    margin-top: 8px;
    font-size: 14px !important;
    font-weight: unset !important;
}

:global(.van-tag--plain) {
    margin-top: 10px;
    border-radius: 4px;
    color: #fffff3 !important;
    background: #ff4344 !important;
    height: 20px !important;
}

:global(.van-card__price) {
    color: #e4393c;
    font-size: 20px;
    font-weight: unset;
}

:global(.van-card__price-currency) {
    font-size: 16px;
}

:global(.van-card__price-integer) {
    font-size: 20px;
}
</style>
