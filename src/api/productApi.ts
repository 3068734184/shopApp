import http from '../http'

export default {
    select: {
        name: "产品列表",
        url: "api/product",
        call: async function (params: any) {
            return await http.get(this.url, params)
        }
    },
    insert: {
        name: "新增产品",
        url: "api/product",
        call: async function (params: any) {
            return await http.post(this.url, params)
        }
    },
    update: {
        name: "修改产品",
        url: "api/product",
        call: async function (params: any) {
            return await http.put(this.url, params)
        }
    },
    delete: {
        name: "删除产品",
        url: "api/product",
        call: async function (params: any) {
            return await http.delete(this.url, params)
        }
    }
}