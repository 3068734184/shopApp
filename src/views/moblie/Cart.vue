<!-- mobileCart -->
<template>
  <div>
    <div>
      <van-nav-bar title="购物车" left-arrow @click-left="onClickLeft" />
    </div>

    <div v-if="emptyCart" class="empty-cart">
      <div class="shopcart-empty-wrap">
        <div class="empty-icon"></div>
        <p class="empty-text">购物车空空如也,去逛逛吧~</p>
      </div>
    </div>


    <!-- 商品列表 -->
    <van-checkbox-group v-model="checkedResult" @change="checkedResultChange">
      <van-checkbox v-for="(item, index) in cartItems" :key="index" :name="item.id" label-disabled>
        <van-card :title="item.name" :thumb="item.img" :price="item.amount">
          <template #tags>
            <van-tag plain type="primary">{{ item.brief }}</van-tag>
          </template>
          <template #footer>
            <van-stepper v-model="item.number" theme="round" button-size="22" disable-input
              @change="updateAmount(item)" />
          </template>
        </van-card>
      </van-checkbox>
    </van-checkbox-group>


    <!-- 底部结算 -->
    <van-submit-bar v-if="!emptyCart" :disabled="isSubmitDisabled" :price="totalPrice" button-text="去结算" @submit="onSubmit">
      <van-checkbox v-model="isCheckAll" :indeterminate="isIndeterminate" @change="checkAllChange">全选</van-checkbox>
      <template #tip>
        你的收货地址不支持配送, <span @click="onClickLink">修改地址</span>
      </template>
    </van-submit-bar>
  </div>
</template>

<script setup lang='ts'>
import { showToast } from 'vant';
import { computed, onMounted, ref, watch } from 'vue';
import { useRouter } from 'vue-router';
import { useCartStore } from "../../store/cart";
import { useOrderStore } from '../../store/order';
import { shoppingCart } from '../../api';

// 返回键
const router = useRouter()
const onClickLeft = () => {
  router.go(-1)
}

// 购物车是否为空
const emptyCart = ref(false)

// 列表复选框
interface CartItem {
  id: number;
  name: string;
  img: string;
  amount: number;
  unit_price: number;
  number: number;
  brief: string;
}
const cartList = ref<CartItem[]>([])

onMounted(() => {
  getCartList()
  // addShoppingCart()

})
// 从pinia获取🛒商品信息
const cartStore = useCartStore()

// 获取购物车🛒列表
const getCartList = () => {
  const userId = sessionStorage.getItem("userId")
  if (userId != null) {
    shoppingCart.selectByUserId.call({
      user_id: userId
    }).then((res) => {
      // console.log("res=>", res);
      cartList.value = res.data as CartItem[];
      cartStore.setItems(cartList.value)
    })
  } else {
    emptyCart.value = true
  }
}

const cartItems = computed(() => cartStore.items)
// console.log("cartItems=>", cartItems);

const isCheckAll = ref(false);
const checkedResult = ref<number[]>([]);
const isIndeterminate = ref(false);
const totalPrice = ref(0);
const isSubmitDisabled = ref(true);

const updateAmount = (item: any) => {
  item.amount = item.number * item.unit_price;

  updatePrice()
}

const updatePrice = () => {
  totalPrice.value = checkedResult.value.reduce((total, id) => {
    const item = cartItems.value.find(item => item.id === id);
    return (total + (item ? item.amount : 0));
  }, 0);
  totalPrice.value = totalPrice.value * 100

  isSubmitDisabled.value = checkedResult.value.length === 0;
};

const checkAllChange = () => {
  checkedResult.value = isCheckAll.value ? cartList.value.map(item => item.id) : [];
  updatePrice();
};

const checkedResultChange = () => {
  updatePrice();
  isIndeterminate.value = checkedResult.value.length > 0 && checkedResult.value.length < cartList.value.length;
  isCheckAll.value = checkedResult.value.length === cartList.value.length;
};

watch(cartItems, updatePrice, { deep: true });
updatePrice();

// 底部按钮
const orderStore = useOrderStore()
const onSubmit = () => {
  const selectedItems = cartList.value.filter(item => checkedResult.value.includes(item.id));
  orderStore.setItems(selectedItems)
  // console.log("...orderStore.items", orderStore.items);
  router.push({ path: "/moveshow/orderConfirm" });
}
const onClickLink = () => showToast('修改地址');
</script>

<style scoped>
.empty-cart {
  background-color: #f2f2f2;
  height: calc(100vh - 50px);
}

.shopcart-empty-wrap {
  padding: 70px 0 65px;
  text-align: center;
}

.empty-icon {
  background: url(//img11.360buyimg.com/jdphoto/s180x180_jfs/t18163/292/540553659/74408/adeb7463/5a93c51cN3bb5e37b.png) no-repeat 50%;
  background-size: 90px 90px;
  display: inline-block;
  height: 90px;
  width: 90px;
}

.empty-text {
  color: #333;
  font-size: 16px;
  line-height: 16px;
  margin-top: 15px;
}

:global(.van-checkbox__label) {
  width: 100%;
  margin-right: 10px;
}

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

:global(.van-card__price-currency) {
  font-size: 16px;
}

:global(.van-card__price-integer) {
  font-size: 20px;
}

:global(.van-checkbox-group) {
  background-color: #f7f8fa;
}

:global(.van-checkbox__icon) {
  margin-left: 10px;
}

:global(.van-submit-bar) {
  bottom: 50px !important;
}
</style>
