import { createPinia, defineStore } from 'pinia';

interface CartItem {
  id: number;
  name: string;
  img: string;
  price: number;
  quantity: number;
  brief: string;
}

export const useCartStore = defineStore('cart', {
  state: () => ({
    cartItems: [] as CartItem[],
  }),
  actions: {
    setCartItems(cartItems: CartItem[]) {
      this.cartItems = cartItems;
    },
  },
  getters: {
    getCartItems: (state) => state.cartItems,
  },
});

const pinia = createPinia();
export default pinia;
