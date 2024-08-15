import http from '../http'

export default {
    selectByUserId: {
        name: "根据user_id查询购物车",
        url: "/api/shoppingCart/selectByUserId",
        call: async function (params: any = {}) {
            return await http.get(this.url, params)
        }
    },
    addShoppingCart: {
        name: "添加购物车",
        url: "/api/shoppingCart/addCart",
        call: async function (params:any = {}) {
            return await http.post(this.url,params)           
        }
    },
    deleteShoppingCart: {
        name: "删除购物车",
        url: "/api/shoppingCart/deleteCart",
        call:async function (params:any={}) {
            return await http.delete(this.url,params)
        }
    }
}