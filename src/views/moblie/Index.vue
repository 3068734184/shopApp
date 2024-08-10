<<<<<<< HEAD
<!-- mobileIndex -->
<template>
    <div>

        <div class="head">
            <van-search show-action v-model="searchInfo" shape="round" background="#ff5454" placeholder="请输入搜索关键词"
                @search="onSearch">
                <template #left>
                    <van-icon name="bars" color="#ffe4e4" style="margin-right: 10px;" />
                </template>
                <template #action>
                    <van-icon name="contact-o" color="#ffe4e4" />
                </template>
            </van-search>
            <van-divider :style="{ color: '#ff8383', borderColor: '#ff8383', padding: '0', margin: '0' }" />
        </div>

        <div class="category-box">
            <div class="box-container">
                <van-swipe style="height: 80px;" indicator-color="#fa2c19">
                    <van-swipe-item>
                        <a href="" class="nav-box" v-for="(item, index) in categoryBox" :key="index">
                            <img :src="item.img" alt="">
                            <span>{{ item.categoryName }}</span>
                        </a>
                    </van-swipe-item>
                    <van-swipe-item>
                        <a href="" class="nav-box" v-for="(item, index) in categoryBox" :key="index">
                            <img :src="item.img" alt="">
                            <span>{{ item.categoryName }}</span>
                        </a>
                    </van-swipe-item>

                </van-swipe>
            </div>
        </div>

        <div v-if="true" class="slideShow">
            <van-swipe :autoplay="3000" lazy-render>
                <van-swipe-item v-for="image in bannerImages" :key="image">
                    <img :src="image" width="100%" />
                </van-swipe-item>
            </van-swipe>
        </div>

        <div class="flash-sale">
            <div class="sale-container">
                <div class="container-desc">
                    <div class="container-title"></div>
                    <div class="container-subtitle">限时低价</div>
                    <div class="container-link">
                        <span>去抢购</span>
                    </div>
                </div>
                <div class="container-goods">
                    <div class="goods-list">
                        <a class="goods-list-item" href="" v-for="item in 10" :key="item">
                            <img src="https://m.360buyimg.com/seckillcms/jfs/t1/167996/11/38682/63814/65114407F5d59a14d/00a4163b62a65ee6.png"
                                alt="">
                            <span><em>￥</em>6.88</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>

        <div class="recommend-goods">
            <van-row>
                <van-col span="12">
                    <van-row>
                        <div class="goods-col" v-for="item in 3" :key="item">
                            <a href="" class="goods">
                                <div class="goods-img bg-stamp">
                                    <div class="img-box">
                                        <img src="https://img14.360buyimg.com/mobilecms/s360x360_jfs/t1/48877/5/24690/32064/669a101cF204b87a2/17c2c13ebb558a5b.jpg!q70.dpg.webp"
                                            alt="" class="shop-img">
                                    </div>
                                </div>
                                <div class="goods-info">
                                    <div class="goods-name">PRADA/普拉达【礼物】新品女士金属徽标中号牛皮革手提单肩包女包 黑色</div>
                                    <div class="goods-row">
                                        <div class="goods-price">￥<em class="goods-price-int">20900</em>.00</div>
                                    </div>
                                    <div class="goods-row-bottom">
                                        <div class="tags-item">
                                            <div class="item-text">31条评论</div>
                                        </div>
                                    </div>
                                </div>
                                <div class="black-point"></div>
                                <div class="goods-back">看相似</div>
                            </a>
                        </div>
                    </van-row>
                </van-col>
                <van-col span="12">
                    <div class="goods-col" v-for="item in 3" :key="item">
                        <a href="" class="goods">
                            <div class="goods-img bg-stamp">
                                <div class="img-box">
                                    <img src="https://img14.360buyimg.com/mobilecms/s360x360_jfs/t1/237893/5/21958/115302/66ab3997Fb55df60a/f092dc7df25ec6d1.jpg!q70.dpg.webp"
                                        alt="" class="shop-img">
                                </div>
                            </div>
                            <div class="goods-info">
                                <div class="goods-name">AMIRO觅光化妆镜带灯led桌面梳妆镜子O2智能补光美妆镜 七夕情人节礼物生日送女友老婆 小黑镜 无线版</div>
                                <div class="goods-row-title-bottom">
                                    <img src="https://img12.360buyimg.com/img/s264x76_jfs/t1/47319/8/18410/7823/642bfbcaFb2428c1a/0ec6a2d6acc3f824.png"
                                        alt="" class="tags-item" style="width: 2.775rem; height: 0.8rem;">
                                </div>
                                <div class="goods-row">
                                    <div class="goods-price">￥<em class="goods-price-int">699</em>.00</div>
                                </div>
                                <div class="goods-row-bottom">
                                    <img src="https://img12.360buyimg.com/img/s48x28_jfs/t1/127567/8/7519/1313/5f165ca9Ea295fca1/b90967cc602f446a.png"
                                        alt="" class="tags-item" style="width: 1.2rem; height: 0.7rem;">
                                    <div class="tags-item">
                                        <div class="item-text">31条评论</div>
                                    </div>
                                </div>
                            </div>
                            <div class="black-point"></div>
                            <div class="goods-back">看相似</div>
                        </a>
                    </div>
                </van-col>
            </van-row>

        </div>

    </div>
</template>

<script setup lang='ts'>
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router';
import { productScreenApi } from '../../api';

let router = useRouter()

const searchInfo = ref('')


// slideShow
const bannerImages = ref()


const onSearch = () => {
    router.push({ path: '/moveshow/list', query: { search: searchInfo.value } })
}

const categoryBox = [
    {
        categoryName: '京东超市',
        img: 'https://m15.360buyimg.com/mobilecms/jfs/t1/187640/12/30456/5256/639c2315Ebc95c142/350a8f0c766f5460.png'
    },
    {
        categoryName: '京东电器',
        img: 'https://m15.360buyimg.com/mobilecms/jfs/t1/149144/13/37547/3299/64b11027F86659ec1/fade439ac90544b7.png'
    },
    {
        categoryName: '服饰美妆',
        img: 'https://m15.360buyimg.com/mobilecms/jfs/t1/195186/28/35854/3469/64d4a1e5Fe388886e/3c6f65ecd0aba98b.png'
    },
    {
        categoryName: '充值中心',
        img: 'https://m15.360buyimg.com/mobilecms/jfs/t1/208529/13/35972/4081/6497ee53F4f8d38d9/c5267cb385f923fe.png'
    },
    {
        categoryName: 'PLUS会员',
        img: 'https://m15.360buyimg.com/mobilecms/jfs/t1/37709/6/15279/6118/60ec1046E4b5592c6/a7d6b66354efb141.png'
    },
]

onMounted(() => {
    getBannerImages()
})

const getBannerImages = () => {
    productScreenApi.bannerImages.call().then((res) => {
        // console.log("res==", res);
        bannerImages.value = res
        // console.log(bannerImages.value);
        
    })
}

</script>

<style scoped>
.van-icon {
    font-size: x-large;
}

:global(.van-search__action) {
    display: flex;
}

:global(.van-search__action:active) {
    background-color: unset;
}

.category-box {
    height: 87px;
    padding: .3rem .5rem;
    background: linear-gradient(180deg, #ff5454 .63%, #f4f4f4 80.65%);
    ;
}

.box-container {
    width: 100%;
    height: 4.2rem;
    border-radius: .6rem;
    background: #fff;
    position: relative;
    overflow: hidden;
}

:global(.van-swipe__indicators) {
    bottom: 0 !important;
}

:global(.van-swipe__indicator) {
    border-radius: 25% !important;
    width: .6rem !important;
    height: 4px !important;
}

.container-swiper {
    backface-visibility: hidden;
    transform: translateX(0%);
    transition: 0.6s;
    height: 4.2rem;
}

.container-nav {
    position: absolute;
    top: 0;
    width: 100%;
    height: 100%;
}

.nav-box {
    width: 20%;
    text-align: center;
    display: block;
    float: left;
    font-size: 0;
    line-height: 0;
}

.nav-box img {
    width: 1.8rem;
    height: 1.7rem;
    margin-top: .6rem;
}

.nav-box span {
    display: block;
    margin-top: .3rem;
    color: #595959;
    text-align: center;
    font-size: .6rem;
    font-style: normal;
    font-weight: 400;
    line-height: .6rem;
}

.container-potpot {
    position: absolute;
    left: 0;
    right: 0;
    text-align: center;
    pointer-events: none;
    z-index: 5;
    bottom: .2rem;
}

.container-pot {
    z-index: 5;
    height: .2rem;
    background-color: rgba(0, 0, 0, .06);
    display: inline-block;
    position: relative;
    border-radius: .6rem;
    overflow: hidden;
}

.container-pot .cur {
    background-color: #fa2c19;
}

.container-pot b {
    position: absolute;
    left: 0;
    top: 0;
    bottom: 0;
    height: 100%;
    width: .6rem;
}

.flash-sale {
    position: relative;
    box-sizing: border-box;
    padding: .3rem .5rem;
}

.sale-container {
    width: 100%;
    height: 4.7rem;
    background-image: url(https://img11.360buyimg.com/img/jfs/t1/226923/11/7714/45152/65782c91F9d91466d/24928a901f4c3f58.png);
    background-size: 100% 100%;
    background-repeat: no-repeat;
    position: relative;
    border-radius: .6rem;
}

.container-desc {
    width: calc(22% - 2px);
    display: flex;
    flex-direction: column;
    float: left;
    align-items: center;
    justify-content: center;
    float: left;
    height: 100%;
}

.container-title {
    width: 3.25rem;
    height: .65rem;
    background-image: url(https://img30.360buyimg.com/img/jfs/t1/227615/23/8080/1616/65782dd8F605d99af/0d97136b32d5ab1b.png);
    background-size: 100% 100%;
    background-repeat: no-repeat;
}

.container-subtitle {
    color: #fff;
    width: 3.25rem;
    font-size: .6rem;
    font-style: normal;
    font-weight: 400;
    line-height: .6rem;
    margin-top: .3rem;
}

.container-link {
    margin-top: .45rem;
    margin-left: -.6rem;
    border-radius: .4rem;
    background: #fff;
    width: 2.65rem;
    height: .8rem;
    text-align: center;
    color: #fa2c19;
    font-size: .5rem;
    font-style: normal;
    font-weight: 400;
    line-height: .8rem;
}

.container-goods {
    float: left;
    width: 78%;
    height: 4.5rem;
    margin-top: 2px;
    margin-right: 2px;
    border-radius: .6rem;
    overflow-x: scroll;
    background: #fff;
    padding: .5rem 0 .3rem;
    box-sizing: border-box;
}

.goods-list {
    width: calc(250%);
    height: 100%;
    position: relative;
    overflow: hidden;
}

.goods-list-item {
    width: calc(10%);
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    float: left;
}

.goods-list-item img {
    width: 2.65rem;
    height: 2.65rem;
}

img {
    border: 0;
    vertical-align: top;
}

.goods-list-item span {
    display: block;
    margin-top: .35rem;
    color: #fa2c19;
    text-align: center;
    font-size: .7rem;
    font-style: normal;
    font-weight: 400;
    line-height: .7rem;
}

.goods-col {
    padding: 0.25rem;
    flex-grow: 1;
}

.goods {
    display: block;
    width: 100%;
    background: #ffffff;
    border-radius: 0.4rem;
    overflow: hidden;
    position: relative;
}

.goods-img {
    margin-bottom: 0.25rem;
    position: relative;
    box-sizing: border-box;
    overflow: hidden;
}

.goods .bg-stamp {
    background: #fff url(https://img10.360buyimg.com/wq/jfs/t24601/190/890984006/4559/731564fc/5b7f9b7bN3ccd29ab.png);
    background-repeat: no-repeat;
    background-position: 50%;
    background-size: 8.625rem;
}

.img-box {
    position: relative;
    width: 100%;
    padding-top: 100%;
}

.shop-img {
    position: absolute;
    z-index: 0;
    top: 0%;
    left: 0%;
    display: block;
    width: 100%;
    height: auto;
    margin: 0 auto;
}

.goods-info {
    padding: 0 0.5rem 0.5rem;
}

.goods-row-title-bottom {
    display: flex;
    align-items: center;
    flex-wrap: wrap;
    overflow: hidden;
    height: 1.25rem;
}

.goods-name {
    text-align: left;
    line-height: 1.05rem;
    height: 2.1rem;
    font-size: 0.7rem;
    font-family: -apple-system, Helvetica, sans-serif;
    overflow: hidden;
    color: #434343;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    word-break: break-all;
    -webkit-box-orient: vertical;
}

.goods-row {
    display: flex;
    height: 1.5rem;
    align-items: center;
    flex-wrap: wrap;
    overflow: hidden;
}

.goods-price {
    font-style: normal;
    font-family: JDZH-Regular, sans-serif;
    display: inline-block;
    font-size: 0.7rem;
    line-height: 1.5rem;
    color: #ff4142;
}

.goods-price-int {
    font-size: 1rem;
    font-style: normal;
}

.goods-row-bottom {
    width: calc(100% - 2.5rem);
    height: 1rem;
    display: flex;
    align-items: center;
    flex-wrap: wrap;
    overflow: hidden;
}

.tags-item {
    max-width: 100%;
    color: #999;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    font-size: 0.6rem;
}

.tags-item:first-child {
    margin-right: 0.025rem
}

.tags-item:not(:first-child) {
    margin-left: 0.3rem;
    margin-right: 0.025rem;
}

:global(.van-search__field .van-field__left-icon) {
    color: #fa3826 !important;
}

:global(.van-field__left-icon .van-icon) {
    font-size: 20px;
    font-weight: 700;
}

.black-point {
    position: absolute;
    right: 0.25rem;
    bottom: 0.25rem;
    width: 0.05rem;
    height: 0.05rem;
    background-color: #d7d7d7;
}

.goods-back {
    position: absolute;
    right: 0.5rem;
    bottom: 0.5rem;
    background: #f2f2f2;
    color: #262626;
    font-size: 0.55rem;
    width: 2.5rem;
    height: 1rem;
    border-radius: 0.5rem;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
=======
<!-- mobileIndex -->
<template>
    <div>

        <div class="head">
            <van-search show-action v-model="searchInfo" shape="round" background="#ff5454" placeholder="请输入搜索关键词"
                @search="onSearch">
                <template #left>
                    <van-icon name="bars" color="#ffe4e4" style="margin-right: 10px;" @click="toCategory" />
                </template>
                <template #action>
                    <van-icon name="contact-o" color="#ffe4e4" @click="toLogin" />
                </template>
            </van-search>
            <van-divider :style="{ color: '#ff8383', borderColor: '#ff8383', padding: '0', margin: '0' }" />
        </div>

        <div class="category-box">
            <div class="box-container">
                <van-swipe style="height: 80px;" indicator-color="#fa2c19">
                    <van-swipe-item>
                        <a href="" class="nav-box" v-for="(item, index) in categoryBox" :key="index">
                            <img :src="item.img" alt="">
                            <span>{{ item.categoryName }}</span>
                        </a>
                    </van-swipe-item>
                    <van-swipe-item>
                        <a href="" class="nav-box" v-for="(item, index) in categoryBox" :key="index">
                            <img :src="item.img" alt="">
                            <span>{{ item.categoryName }}</span>
                        </a>
                    </van-swipe-item>

                </van-swipe>
            </div>
        </div>

        <div v-if="true" class="slideShow">
            <van-swipe :autoplay="3000" lazy-render>
                <van-swipe-item v-for="image in bannerImages" :key="image">
                    <img :src="image" width="100%" />
                </van-swipe-item>
            </van-swipe>
        </div>

        <div class="flash-sale">
            <div class="sale-container">
                <div class="container-desc">
                    <div class="container-title"></div>
                    <div class="container-subtitle">限时低价</div>
                    <div class="container-link">
                        <span>去抢购</span>
                    </div>
                </div>
                <div class="container-goods">
                    <div class="goods-list">
                        <a class="goods-list-item" href="" v-for="item in 10" :key="item">
                            <img src="https://m.360buyimg.com/seckillcms/jfs/t1/167996/11/38682/63814/65114407F5d59a14d/00a4163b62a65ee6.png"
                                alt="">
                            <span><em>￥</em>6.88</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>

        <div class="recommend-goods">
            <van-row>
                <van-col span="12">
                    <van-row>
                        <div class="goods-col" v-for="item in 3" :key="item">
                            <a href="" class="goods">
                                <div class="goods-img bg-stamp">
                                    <div class="img-box">
                                        <img src="https://img14.360buyimg.com/mobilecms/s360x360_jfs/t1/48877/5/24690/32064/669a101cF204b87a2/17c2c13ebb558a5b.jpg!q70.dpg.webp"
                                            alt="" class="shop-img">
                                    </div>
                                </div>
                                <div class="goods-info">
                                    <div class="goods-name">PRADA/普拉达【礼物】新品女士金属徽标中号牛皮革手提单肩包女包 黑色</div>
                                    <div class="goods-row">
                                        <div class="goods-price">￥<em class="goods-price-int">20900</em>.00</div>
                                    </div>
                                    <div class="goods-row-bottom">
                                        <div class="tags-item">
                                            <div class="item-text">31条评论</div>
                                        </div>
                                    </div>
                                </div>
                                <div class="black-point"></div>
                                <div class="goods-back">看相似</div>
                            </a>
                        </div>
                    </van-row>
                </van-col>
                <van-col span="12">
                    <div class="goods-col" v-for="item in 3" :key="item">
                        <a href="" class="goods">
                            <div class="goods-img bg-stamp">
                                <div class="img-box">
                                    <img src="https://img14.360buyimg.com/mobilecms/s360x360_jfs/t1/237893/5/21958/115302/66ab3997Fb55df60a/f092dc7df25ec6d1.jpg!q70.dpg.webp"
                                        alt="" class="shop-img">
                                </div>
                            </div>
                            <div class="goods-info">
                                <div class="goods-name">AMIRO觅光化妆镜带灯led桌面梳妆镜子O2智能补光美妆镜 七夕情人节礼物生日送女友老婆 小黑镜 无线版</div>
                                <div class="goods-row-title-bottom">
                                    <img src="https://img12.360buyimg.com/img/s264x76_jfs/t1/47319/8/18410/7823/642bfbcaFb2428c1a/0ec6a2d6acc3f824.png"
                                        alt="" class="tags-item" style="width: 2.775rem; height: 0.8rem;">
                                </div>
                                <div class="goods-row">
                                    <div class="goods-price">￥<em class="goods-price-int">699</em>.00</div>
                                </div>
                                <div class="goods-row-bottom">
                                    <img src="https://img12.360buyimg.com/img/s48x28_jfs/t1/127567/8/7519/1313/5f165ca9Ea295fca1/b90967cc602f446a.png"
                                        alt="" class="tags-item" style="width: 1.2rem; height: 0.7rem;">
                                    <div class="tags-item">
                                        <div class="item-text">31条评论</div>
                                    </div>
                                </div>
                            </div>
                            <div class="black-point"></div>
                            <div class="goods-back">看相似</div>
                        </a>
                    </div>
                </van-col>
            </van-row>

        </div>

    </div>
</template>

<script setup lang='ts'>
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router';
import { productScreenApi } from '../../api';

let router = useRouter()

const searchInfo = ref('')


// slideShow
const bannerImages = ref()


const onSearch = () => {
    router.push({ path: '/moveshow/list', query: { search: searchInfo.value } })
}

const categoryBox = [
    {
        categoryName: '京东超市',
        img: 'https://m15.360buyimg.com/mobilecms/jfs/t1/187640/12/30456/5256/639c2315Ebc95c142/350a8f0c766f5460.png'
    },
    {
        categoryName: '京东电器',
        img: 'https://m15.360buyimg.com/mobilecms/jfs/t1/149144/13/37547/3299/64b11027F86659ec1/fade439ac90544b7.png'
    },
    {
        categoryName: '服饰美妆',
        img: 'https://m15.360buyimg.com/mobilecms/jfs/t1/195186/28/35854/3469/64d4a1e5Fe388886e/3c6f65ecd0aba98b.png'
    },
    {
        categoryName: '充值中心',
        img: 'https://m15.360buyimg.com/mobilecms/jfs/t1/208529/13/35972/4081/6497ee53F4f8d38d9/c5267cb385f923fe.png'
    },
    {
        categoryName: 'PLUS会员',
        img: 'https://m15.360buyimg.com/mobilecms/jfs/t1/37709/6/15279/6118/60ec1046E4b5592c6/a7d6b66354efb141.png'
    },
]

onMounted(() => {
    getBannerImages()
})

const getBannerImages = () => {
    productScreenApi.bannerImages.call().then((res) => {
        // console.log("res==", res);
        bannerImages.value = res.data
        // console.log(bannerImages.value);
        
    })
}

const toLogin = () =>{
    router.push({path:"/moveshow/login"})
}

const toCategory = () =>{
    router.push({path:"/moveshow/category"})
}
</script>

<style scoped>
.van-icon {
    font-size: x-large;
}

:global(.van-search__action) {
    display: flex;
}

:global(.van-search__action:active) {
    background-color: unset;
}

.category-box {
    height: 87px;
    padding: .3rem .5rem;
    background: linear-gradient(180deg, #ff5454 .63%, #f4f4f4 80.65%);
    ;
}

.box-container {
    width: 100%;
    height: 4.2rem;
    border-radius: .6rem;
    background: #fff;
    position: relative;
    overflow: hidden;
}

:global(.van-swipe__indicators) {
    bottom: 0 !important;
}

:global(.van-swipe__indicator) {
    border-radius: 25% !important;
    width: .6rem !important;
    height: 4px !important;
}

.container-swiper {
    backface-visibility: hidden;
    transform: translateX(0%);
    transition: 0.6s;
    height: 4.2rem;
}

.container-nav {
    position: absolute;
    top: 0;
    width: 100%;
    height: 100%;
}

.nav-box {
    width: 20%;
    text-align: center;
    display: block;
    float: left;
    font-size: 0;
    line-height: 0;
}

.nav-box img {
    width: 1.8rem;
    height: 1.7rem;
    margin-top: .6rem;
}

.nav-box span {
    display: block;
    margin-top: .3rem;
    color: #595959;
    text-align: center;
    font-size: .6rem;
    font-style: normal;
    font-weight: 400;
    line-height: .6rem;
}

.container-potpot {
    position: absolute;
    left: 0;
    right: 0;
    text-align: center;
    pointer-events: none;
    z-index: 5;
    bottom: .2rem;
}

.container-pot {
    z-index: 5;
    height: .2rem;
    background-color: rgba(0, 0, 0, .06);
    display: inline-block;
    position: relative;
    border-radius: .6rem;
    overflow: hidden;
}

.container-pot .cur {
    background-color: #fa2c19;
}

.container-pot b {
    position: absolute;
    left: 0;
    top: 0;
    bottom: 0;
    height: 100%;
    width: .6rem;
}

.flash-sale {
    position: relative;
    box-sizing: border-box;
    padding: .3rem .5rem;
}

.sale-container {
    width: 100%;
    height: 4.7rem;
    background-image: url(https://img11.360buyimg.com/img/jfs/t1/226923/11/7714/45152/65782c91F9d91466d/24928a901f4c3f58.png);
    background-size: 100% 100%;
    background-repeat: no-repeat;
    position: relative;
    border-radius: .6rem;
}

.container-desc {
    width: calc(22% - 2px);
    display: flex;
    flex-direction: column;
    float: left;
    align-items: center;
    justify-content: center;
    float: left;
    height: 100%;
}

.container-title {
    width: 3.25rem;
    height: .65rem;
    background-image: url(https://img30.360buyimg.com/img/jfs/t1/227615/23/8080/1616/65782dd8F605d99af/0d97136b32d5ab1b.png);
    background-size: 100% 100%;
    background-repeat: no-repeat;
}

.container-subtitle {
    color: #fff;
    width: 3.25rem;
    font-size: .6rem;
    font-style: normal;
    font-weight: 400;
    line-height: .6rem;
    margin-top: .3rem;
}

.container-link {
    margin-top: .45rem;
    margin-left: -.6rem;
    border-radius: .4rem;
    background: #fff;
    width: 2.65rem;
    height: .8rem;
    text-align: center;
    color: #fa2c19;
    font-size: .5rem;
    font-style: normal;
    font-weight: 400;
    line-height: .8rem;
}

.container-goods {
    float: left;
    width: 78%;
    height: 4.5rem;
    margin-top: 2px;
    margin-right: 2px;
    border-radius: .6rem;
    overflow-x: scroll;
    background: #fff;
    padding: .5rem 0 .3rem;
    box-sizing: border-box;
}

.goods-list {
    width: calc(250%);
    height: 100%;
    position: relative;
    overflow: hidden;
}

.goods-list-item {
    width: calc(10%);
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    float: left;
}

.goods-list-item img {
    width: 2.65rem;
    height: 2.65rem;
}

img {
    border: 0;
    vertical-align: top;
}

.goods-list-item span {
    display: block;
    margin-top: .35rem;
    color: #fa2c19;
    text-align: center;
    font-size: .7rem;
    font-style: normal;
    font-weight: 400;
    line-height: .7rem;
}

.goods-col {
    padding: 0.25rem;
    flex-grow: 1;
}

.goods {
    display: block;
    width: 100%;
    background: #ffffff;
    border-radius: 0.4rem;
    overflow: hidden;
    position: relative;
}

.goods-img {
    margin-bottom: 0.25rem;
    position: relative;
    box-sizing: border-box;
    overflow: hidden;
}

.goods .bg-stamp {
    background: #fff url(https://img10.360buyimg.com/wq/jfs/t24601/190/890984006/4559/731564fc/5b7f9b7bN3ccd29ab.png);
    background-repeat: no-repeat;
    background-position: 50%;
    background-size: 8.625rem;
}

.img-box {
    position: relative;
    width: 100%;
    padding-top: 100%;
}

.shop-img {
    position: absolute;
    z-index: 0;
    top: 0%;
    left: 0%;
    display: block;
    width: 100%;
    height: auto;
    margin: 0 auto;
}

.goods-info {
    padding: 0 0.5rem 0.5rem;
}

.goods-row-title-bottom {
    display: flex;
    align-items: center;
    flex-wrap: wrap;
    overflow: hidden;
    height: 1.25rem;
}

.goods-name {
    text-align: left;
    line-height: 1.05rem;
    height: 2.1rem;
    font-size: 0.7rem;
    font-family: -apple-system, Helvetica, sans-serif;
    overflow: hidden;
    color: #434343;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    word-break: break-all;
    -webkit-box-orient: vertical;
}

.goods-row {
    display: flex;
    height: 1.5rem;
    align-items: center;
    flex-wrap: wrap;
    overflow: hidden;
}

.goods-price {
    font-style: normal;
    font-family: JDZH-Regular, sans-serif;
    display: inline-block;
    font-size: 0.7rem;
    line-height: 1.5rem;
    color: #ff4142;
}

.goods-price-int {
    font-size: 1rem;
    font-style: normal;
}

.goods-row-bottom {
    width: calc(100% - 2.5rem);
    height: 1rem;
    display: flex;
    align-items: center;
    flex-wrap: wrap;
    overflow: hidden;
}

.tags-item {
    max-width: 100%;
    color: #999;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    font-size: 0.6rem;
}

.tags-item:first-child {
    margin-right: 0.025rem
}

.tags-item:not(:first-child) {
    margin-left: 0.3rem;
    margin-right: 0.025rem;
}

:global(.van-search__field .van-field__left-icon) {
    color: #fa3826 !important;
}

:global(.van-field__left-icon .van-icon) {
    font-size: 20px;
    font-weight: 700;
}

.black-point {
    position: absolute;
    right: 0.25rem;
    bottom: 0.25rem;
    width: 0.05rem;
    height: 0.05rem;
    background-color: #d7d7d7;
}

.goods-back {
    position: absolute;
    right: 0.5rem;
    bottom: 0.5rem;
    background: #f2f2f2;
    color: #262626;
    font-size: 0.55rem;
    width: 2.5rem;
    height: 1rem;
    border-radius: 0.5rem;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
>>>>>>> 67571dec71b6ab3fc294f8acffcbe2f29a9c63cf
