import http from "../http"

export default {
    addAddress: {
        name: "添加地址",
        url: "/api/addressBook/addAddress",
        call: async function (params: any = {}) {
            return await http.post(this.url, params)
        }
    },
    selectById: {
        name: "根据id查询",
        url: "/api/addressBook/selectById",
        call: async function (params: any = {}) {
            return await http.get(this.url, params)
        }
    },
    selectAddressBooks: {
        name: "根据userid查询",
        url: "/api/addressBook/selectAddressBooksByUserId",
        call: async function (params: any = {}) {
            return await http.get(this.url, params)
        }
    },
    deleteById: {
        name: "根据id删除",
        url: "/api/addressBook/deleteById",
        call: async function (params: any = {}) {
            return await http.delete(this.url, params)
        }
    }
}