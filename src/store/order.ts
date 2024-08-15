import { defineStore } from 'pinia'

interface OrderItem {
    id: number;
    name: string;
    img: string;
    number: number;
    amount: number;
    brief: string;
}

interface OrderState {
    items: OrderItem[];
}

export const useOrderStore = defineStore({
    id: 'order',
    state: (): OrderState => ({
        items: []
    }),
    actions: {
        setItems(items: OrderItem[]) {
            this.items = items
        },
        clearItems() {
            this.items = []
        }
    },
    persist: true,
})