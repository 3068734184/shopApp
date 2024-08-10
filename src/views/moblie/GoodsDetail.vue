<!-- goodsDetail -->
<template>
    <div>
        <van-swipe>
            <van-swipe-item v-for="i in 4" :key="i">
                <div class="goods-banner-img">
                    <img style="width: 100%;"
                        src="https://m.360buyimg.com/mobilecms/s843x843_jfs/t1/3874/19/23294/64685/66b4469dF07603990/a106c38ed9055c8b.jpg!q70.dpg.webp"
                        alt="">
                </div>
            </van-swipe-item>
            <template #indicator="{ active, total }">
                <div class="custom-indicator">{{ active + 1 }}/{{ total }}</div>
            </template>
        </van-swipe>

        <div class="buy-area">
            <div class="priceWrap">
                <div class="price-wrap">
                    <span class="price">
                        <span style="margin-right: 2px; font-size: 12px; color: inherit;">到手价</span>
                        ￥<em>79</em>
                        <span class="price-demicals">.00</span>
                    </span>
                    <span class="orgin-price">
                        <span class="price-crossout"
                            style="position: relative; display: inline-block; vertical-align: middle; margin-right: 5px;"></span>
                        ￥<em>109</em>
                        <span class="price-demicals">.00</span>
                    </span>
                </div>
            </div>
            <div class="fn-wrap">
                <div class="fn-goods-name">
                    <span>弱水时砂甲虫真无线耳机半入耳游戏电竞吃鸡超长续航蓝牙5.3耳机 HiFi高音质 运动跑步 通话降噪 月银-新版</span>
                </div>
            </div>
            <div class="favFloor">
                <div class="buttons">
                    <div class="buttons-icon">
                        <div class="icon-with-text">
                            <svg class="icon" aria-hidden="true">
                                <use xlink:href="#icon-fenxiang"></use>
                            </svg>
                            <span>分享</span>
                        </div>
                        <div class="icon-with-text">
                            <svg class="icon" aria-hidden="true">
                                <use xlink:href="#icon-shoucang"></use>
                            </svg>
                            <span>收藏</span>
                        </div>
                        <div class="icon-with-text">
                            <svg class="icon" aria-hidden="true">
                                <use xlink:href="#icon-jiangdichengben"></use>
                            </svg>
                            <span>降价通知</span>
                        </div>
                    </div>
                    <div class="buttons-title"></div>
                </div>
            </div>
        </div>

        <!-- 优惠楼层 -->
        <div class="item-floor">
            <!-- 优惠券单元格 -->
            <van-cell-group inset>
                <van-coupon-cell :coupons="coupons" :chosen-coupon="chosenCoupon" @click="showList = true" />
            </van-cell-group>
            <!-- 优惠券列表 -->
            <van-popup v-model:show="showList" round position="bottom" style="height: 60%; padding-top: 4px;">
                <van-coupon-list :coupons="coupons" :chosen-coupon="chosenCoupon" :disabled-coupons="disabledCoupons"
                    @change="onChange" @exchange="onExchange" />
            </van-popup>

        </div>
        <!-- sku楼层 -->
        <div class="item-floor">
            <van-cell-group inset>
                <van-cell is-link arrow-direction="down" @click="show = true" >
                    <!-- 使用 title 插槽来自定义标题 -->
                    <template #title>
                        <span class="custom-title">已选</span>
                        <span>月银-新版，1个</span>
                    </template>
                </van-cell>
            </van-cell-group>
            <van-action-sheet v-model:show="show" title="标题" style="height: 60%;">
                <div class="content">内容</div>
            </van-action-sheet>
        </div>

    </div>
</template>

<script setup lang='ts'>
import { inject, onBeforeUnmount, onMounted, ref } from 'vue';

const toggleTabBar = inject<((show: boolean) => void)>('toggleTabBar');
if (toggleTabBar) {
    onMounted(() => {
        toggleTabBar(false)
    });
    onBeforeUnmount(() => {
        toggleTabBar(true);
    });
}


// 优惠楼层相关
const coupon = {
    available: 1,
    condition: '满109减30元',
    reason: '',
    value: 3000,
    name: '促销',
    startAt: 1489104000,
    endAt: 1514592000,
    valueDesc: '30',
    unitDesc: '元',
};

const coupons = ref([coupon]);
const showList = ref(false);
const chosenCoupon = ref(-1);

const onChange = (index: any) => {
    showList.value = false;
    chosenCoupon.value = index;
};
const onExchange = (code: any) => {
    coupons.value.push(coupon);
};

// sku楼层相关
const show = ref(false);
</script>

<style scoped>
:global(body) {
    background-color: #f7f8fa00;
}

.custom-indicator {
    position: absolute;
    right: 5px;
    bottom: 5px;
    padding: 2px 5px;
    font-size: 12px;
    background: rgba(0, 0, 0, 0.1);
}

.goods-banner-img {
    width: 375px;
}

.buy-area {
    background: #fff;
    border-bottom-left-radius: 12px;
    border-bottom-right-radius: 12px;
    overflow: hidden;
}

.price-wrap {
    margin-bottom: 0;
    margin-top: 12px;
    position: relative;
    padding: 0px 20px 0px;
    display: flex;
    flex-wrap: wrap;
    align-items: baseline;
}

.price {
    color: #f2270c;
    display: inline-block;
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    font-size: 16px;
    line-height: 30px;
    font-weight: 600;
}

.orgin-price {
    color: #f2270c;
    display: inline-block;
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    font-size: 16px;
    font-weight: 600;
    margin-left: 10px;
}

.price-crossout::before {
    content: "";
    position: absolute;
    top: 40%;
    left: 10px;
    right: 0;
    height: 1px;
    width: 70px;
    background-color: red;
    transform: translateY(-50%);
}

.price-crossout {
    display: inline-block;
    position: relative;
    height: 1em;
    vertical-align: middle;
}

.price em {
    font-size: 30px;
    font-style: normal;
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}

.fn-wrap {
    padding: 12px 18px;
    position: relative;
    overflow: hidden;
}

.fn-goods-name {
    font-weight: 700;
    line-height: 21px;
    padding-right: 0;
    position: relative;
    display: flex;
    align-items: center;
    min-height: 36px;
    font-size: 16px;
    overflow: hidden;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    display: -webkit-box;
    overflow: hidden;
    text-overflow: ellipsis;
}

.buttons-icon {
    display: flex;
    justify-content: space-around;
    margin-bottom: 15px;
}

.icon-with-text {
    display: flex;
    margin-right: 10px;
}

.icon-with-text span {
    padding-left: 5px;
    font-size: 14px;
}

.item-floor {
    padding-top: 10px;
    background-color: #f7f7f7;
}

.custom-title{
    font-weight: 600;
    margin-right: 10px;
}

.item-floor+.item-floor {
    padding-bottom: 5px;
}

:global(.van-cell-group--inset) {
    margin: 0;
}
</style>