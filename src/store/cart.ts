import { defineStore } from 'pinia';

interface CartItem {
    id: number;
    name: string;
    img: string;
    number: number;
    amount: number;
    brief: string;
}

interface CartState {
    items: CartItem[];
}

export const useCartStore = defineStore({
    id: 'cart',
    state: (): CartState => ({
        items: []
    }),
    actions: {
        addItem(item: CartItem) {
            this.items.push(item);
        },
        addItems(items: CartItem[]) {
            this.items.push(...items)
        },
        setItems(items: CartItem[]) {
            this.items = items
        },
        clearItems() {
            this.items = []
        }
    },
    persist: true,
});
