<!-- orderDetail -->
<template>
    <div>
        <div>
            <van-nav-bar title="确认订单" left-arrow @click-left="onClickLeft" />
        </div>

        <!-- 地址 -->
        <div class="goods-list">
            <van-contact-card type="edit" name="张三" tel="13000000000" :editable="false" />
        </div>

        <div v-for="(item, index) in cartList" :key="index" :name="item.id">
            <van-card :title="item.name" :thumb="item.img" :price="item.price" :num="item.quantity">
                <template #tags>
                    <van-tag plain type="primary">{{ item.brief }}</van-tag>
                </template>
            </van-card>
        </div>

        <van-submit-bar :price="totalPrice" button-text="在线支付" @submit="onSubmit" />

    </div>
</template>

<script setup lang='ts'>
import { showToast } from 'vant';
import { inject, onBeforeUnmount, onMounted, computed } from 'vue';
import { useRouter } from 'vue-router';
import { useCartStore } from "../../store";

const store = useCartStore();

// 返回键
const router = useRouter()
const onClickLeft = () => {
    router.go(-1)
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


const cartList = computed(() => store.getCartItems);
const totalPrice = computed(() => {
    return (cartList.value.reduce((total, item) => total + item.price * item.quantity, 0))*100;
});


const onSubmit = () => showToast('点击按钮');

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

:global(.van-submit-bar__text) {
    text-align: unset !important
}

:global(.van-submit-bar__bar .van-submit-bar__text>span:first-child) {
    display: none;
}
</style>
