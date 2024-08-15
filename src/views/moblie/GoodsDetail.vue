<!-- goodsDetail -->
<template>
    <div>
        <van-floating-bubble icon="arrow-left" @click="floatClick" />

        <van-swipe>
            <van-swipe-item v-for="i in 4" :key="i">
                <div class="goods-banner-img">
                    <img style="width: 100%;" :src="goodsDetail.img" alt="">
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
                        ￥<em>{{ goodsDetail.price }}</em>
                        <span class="price-demicals">.00</span>
                    </span>
                </div>
            </div>
            <div class="fn-wrap">
                <div class="fn-goods-name">
                    <span>{{ goodsDetail.name }}</span>
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
            <van-coupon-cell :coupons="coupons" :chosen-coupon="chosenCoupon" @click="showList = true" />
            <!-- 优惠券列表 -->
            <van-popup v-model:show="showList" round position="bottom" style="height: 60%; padding-top: 4px;">
                <van-coupon-list :coupons="coupons" :chosen-coupon="chosenCoupon" :disabled-coupons="disabledCoupons"
                    @change="onChange" @exchange="onExchange" />
            </van-popup>


        </div>
        <!-- sku楼层 -->
        <div class="item-floor" style="height: 250px;">
            <van-cell-group inset>
                <van-cell is-link arrow-direction="down">
                    <!-- 使用 title 插槽来自定义标题 -->
                    <template #title>
                        <span class="custom-title">已选</span>
                        <span>{{ goodsDetail.brief }}，1辆</span>
                    </template>
                </van-cell>
            </van-cell-group>
            <van-action-sheet v-model:show="showSku" title="标题" style="height: 60%;">
                <div class="content">内容</div>
            </van-action-sheet>
            <van-cell-group inset>
                <van-cell is-link arrow-direction="down" @click="showAddr = true"
                    label="现在下单，承诺8月12日24:00前发货，预计8月15日24:00前送达">
                    <!-- 使用 title 插槽来自定义标题 -->
                    <template #title>
                        <span class="custom-title">送至</span>
                        <span>{{ deliverTo }}</span>
                    </template>
                </van-cell>
                <van-cell>
                    <!-- 使用 title 插槽来自定义标题 -->
                    <template #title>
                        <span class="custom-title">运费</span>
                        <span>包邮（请以提交订单时为准）</span>
                    </template>
                </van-cell>
            </van-cell-group>
            <van-action-sheet v-model:show="showAddr" title="配送至" style="height: 60%;">
                <van-address-list v-model="chosenAddressId" :list="list" :disabled-list="disabledList"
                    disabled-text="以下地址超出配送范围" default-tag-text="默认" @select="bringAddress" @add="onAdd"
                    @edit="onEdit" />
            </van-action-sheet>
            <!-- 地址选择 -->
            <van-popup v-model:show="showAddressSelect" round position="bottom" :style="{ height: '40%' }">
                <van-area v-model="checkedAddressCode" title="标题" :area-list="areaList"
                    :columns-placeholder="['省份', '城市', '区县']" @confirm="addAddress" />
            </van-popup>
        </div>

        <van-action-bar>
            <van-action-bar-icon icon="shop-o" text="店铺" />
            <van-action-bar-icon icon="chat-o" text="客服" />
            <van-action-bar-icon icon="cart-o" text="购物车" :to="{ path: '/moveshow/cart' }" />
            <van-action-bar-button type="warning" text="加入购物车" @click="addToCart" />
            <van-action-bar-button type="danger" text="立即购买" />
        </van-action-bar>
    </div>
</template>

<script setup lang='ts'>
import { inject, onBeforeUnmount, onMounted, reactive, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { productApi, addressBook, shoppingCart } from '../../api';
import { areaList } from '@vant/area-data';
import { showToast } from 'vant';

const route = useRoute()
const router = useRouter()

const floatClick = () => {
    router.go(-1)
}

const productId = ref(route.params.id)

onMounted(() => {
    getProductDetail()
})

const goodsDetail = reactive({
    id: 0,
    name: '',
    price: 0,
    img: '',
    brief: ''
})

interface goodsDetailResponse {
    id: number;
    name: string;
    price: number;
    img: string;
    brief: string;
}
const getProductDetail = () => {
    productApi.selectById.call({
        id: productId.value
    }).then((res) => {
        // console.log("res=>", res);
        const items = res.data as goodsDetailResponse
        goodsDetail.name = items.name
        goodsDetail.price = items.price
        goodsDetail.img = items.img
        goodsDetail.brief = items.brief

    })
}


const addToCart = () => {
    if (sessionStorage.getItem("userId") == null) {
        showToast("请先登录")
    } else {
        shoppingCart.addShoppingCart.call({
            product_id: productId.value,
            user_id: sessionStorage.getItem("userId")
        }).then((res) => {
            // console.log(res);
            if (res.data === 1) {
                showToast("已添加到购物车🛒")
            } else {
                showToast("添加失败")
            }
        })
    }

}


// 隐藏底部导航
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
const disabledCoupons = ref([coupon]);

const onChange = (index: any) => {
    showList.value = false;
    chosenCoupon.value = index;
};
const onExchange = () => {
    coupons.value.push(coupon);
};

// sku楼层相关
const showSku = ref(false);
const showAddr = ref(false);
const showAddressSelect = ref(false)

// 地址相关
const chosenAddressId = ref('2');
const list = ref();
const disabledList = [
    {
        id: '3',
        name: '王五',
        tel: '1320000000',
        address: '浙江省杭州市滨江区江南大道 15 号',
    },
];

const checkedAddressCode = ref()

const deliverTo = ref("北京朝阳区三环到四环之间")
const bringAddress = (item: any) => {
    // console.log("item=>", item.address);
    deliverTo.value = item.address
    // console.log(deliverTo.value);
    showAddr.value = false

}

const onAdd = () => {
    showAddressSelect.value = true
}
const onEdit = (index: any) => console.log('编辑地址:' + index);

// 添加地址
const addAddress = (selectedValues: any) => {
    const name = selectedValues.selectedOptions.map((item: any) => item.text)
    addressBook.addAddress.call({
        user_id: sessionStorage.getItem("userId"),
        consignee: sessionStorage.getItem("username"),
        tel: sessionStorage.getItem("tel"),
        province_name: name[0],
        city_name: name[1],
        district_name: name[2],
        detail: '',
        is_default: 0
    }).then((res) => {
        console.log("res=>", res);
        getAddress()
    })
    showAddressSelect.value = false
}

// 获取地址
const getAddress = () => {
    const userId = sessionStorage.getItem("userId")
    // console.log("userId=>", userId);
    addressBook.selectAddressBooks.call({
        userId: userId
    }).then((res) => {
        // console.log("res=>", res);
        list.value = res.data
    })
};
getAddress()

</script>

<style scoped>
:global(body) {
    background-color: #f7f8fa00;
}

:global(.van-floating-bubble) {
    transform: translate3d(10px, 10px, 0) !important;
    width: 40px !important;
    height: 40px !important;
    background: #666 !important
}

:global(.van-floating-bubble__icon) {
    margin-right: 4px;
}

:global(.van-cell__label) {
    margin-left: 40px;
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

.custom-title {
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