import http from "../http";

export default {
    login: {
        name: "用户登录",
        url: "/api/login",
        call: async function (params: any = {}) {
            return await http.post(this.url, params)
        }
    },
    register: {
        name: "用户注册",
        url: "/api/register",
        call: async function (params: any = {}) {
            return await http.post(this.url, params)
        }
    }
}