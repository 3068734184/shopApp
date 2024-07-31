import http from "../http";

//要默认导出 api接口信息
export default {

    //后台的url api地址连接
    cateCount: {
        name: "类目数据统计",
        url: "/api/cateCount",
        call: async function (params: any = {}) {
            return await http.get(this.url,params)
        }
    },
    todayCount: {
        name: "类目数据统计",
        url: "/api/todayCount",
        call: async function (params: any = {}) {
            return await http.get(this.url, params)
        }
    },
    proCount: {
        name: "类目数据统计",
        url: "/api/proCount",
        call: async function (params: any = {}) {
            return await http.get(this.url, params)
        }
    },
    proTodayCount: {
        name: "类目数据统计",
        url: "/api/proTodayCount",
        call: async function (params: any = {}) {
            return await http.get(this.url, params)
        }
    }

}