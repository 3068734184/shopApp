<!-- orderDetail -->
<template>
    <div>
        <div>
            <van-nav-bar title="确认订单" left-arrow @click-left="onClickLeft" />
        </div>

        <!-- 地址 -->
        <div class="goods-list">
            <div class="address-container">
                <div class="address-header">
                    <van-tag type="danger" style="margin-right: 10px;">默认</van-tag>
                    <van-tag type="primary">学校</van-tag>
                </div>
                <div class="address-info">
                    <p style="font-weight: 700; font-size: 18px;">浙江省 杭州市 西湖区 xx小区xx单元xxx</p>
                    <p style="font-size: 16px; margin-top: 10px;">lisi 13461034141</p>
                </div>
            </div>
        </div>

        <div v-for="(item, index) in cartList" :key="index" :name="item.id">
            <van-card :title="item.name" :thumb="item.img" :price="item.amount" :num="item.number">
                <template #tags>
                    <van-tag plain type="primary">{{ item.brief }}</van-tag>
                </template>
            </van-card>
        </div>

        <van-cell-group>
            <van-cell title="商品金额">
                <template #value>
                    ￥{{ before_Price }}.00
                </template>
            </van-cell>
            <van-cell title="运费" value="￥0.00" />
            <van-cell title="优惠券" value="无可用" is-link />
            <van-cell title="京豆(共75个)" value="不可用" is-link />
            <van-cell title="礼品卡(京东卡/E卡)" value="无可用" is-link />
            <van-cell>
                <template #value>
                    合计: <span style="color: #fa2c19; font-weight: 600; font-size: 16px">￥{{ before_Price }}.00</span>
                </template>
            </van-cell>
        </van-cell-group>


        <van-submit-bar :price="totalPrice" button-text="在线支付" @submit="onSubmit" />

    </div>
</template>

<script setup lang='ts'>
import { showFailToast, showSuccessToast, showToast } from 'vant';
import { inject, onBeforeUnmount, onMounted, computed } from 'vue';
import { useRouter } from 'vue-router';
import { useOrderStore } from "../../store/order";
import { showConfirmDialog } from 'vant';
import { addressBook } from '../../api';

const orderStore = useOrderStore();

// 返回键
const router = useRouter()
const onClickLeft = () => {
    showConfirmDialog({
        title: '确定要放弃付款吗?',
        message:
            '好货不等人, 请尽快支付~',
        theme: 'round-button',
        confirmButtonText: '继续支付',
        cancelButtonText: '忍痛离开'
    }).then(() => {
        // on confirm
    }).catch(() => {
        // on cancel
        orderStore.clearItems()
        router.go(-1)
    });
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


const cartList = orderStore.items;
const before_Price = computed(() => {
    return (cartList.reduce((total, item) => total + item.amount, 0));
});
const totalPrice = before_Price.value * 100

const onSubmit = () => {
    showConfirmDialog({
        title: '确认支付',
        theme: 'round-button',
        confirmButtonText: '立即支付',
        cancelButtonText: '再想想'
    }).then(() => {
        // on confirm
        showSuccessToast('支付成功')
    }).catch(() => {
        // on cancel
        showFailToast('支付取消')
    });
}

onMounted(() => {
    getAddress()
})

// 地址
const getAddress = () => {
    const userId = sessionStorage.getItem("userId")
    if (userId == null) {
        showToast("请先登录")
    } else {
        addressBook.selectAddressBooks.call({
            userId: userId
        }).then((res) => {
            console.log("...res", res);

        })
    }
}



</script>

<style scoped>
:global(.van-card) {
    padding: 10px 0 5px 10px;
}

:global(.van-card__content) {
    justify-content: space-evenly;
}

:global(.van-card__header .van-card__content>div:first-child) {
    display: flex;
    margin-top: 10px;
    align-items: center;
}

:global(.van-card__thumb) {
    width: inherit !important;
}

:global(.van-card__title) {
    margin-right: 10px;
    font-size: 14px !important;
    font-weight: unset !important;
}

:global(.van-tag--plain) {
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

:global(.van-card__num) {
    font-size: 20px;
    margin-right: 15px
}

:global(.van-card__price-currency) {
    font-size: 16px;
}

:global(.van-card__price-integer) {
    font-size: 20px;
}

.van-submit-bar {
    bottom: 0 !important;
}

:global(.van-submit-bar__text) {
    text-align: unset !important
}

:global(.van-submit-bar__bar .van-submit-bar__text>span:first-child) {
    display: none;
}

.address-container {
    padding: 16px;
    background-color: #fff;
    border-bottom: 1px solid #eee;
}

.address-header {
    display: flex;
    align-items: center;
    margin-bottom: 8px;
}

.label {
    font-weight: bold;
    color: #333;
}

.default {
    color: #ff4d4f;
    font-size: 12px;
}

.address-info p {
    margin: 0;
    color: #1a1a1a;
}
</style>
