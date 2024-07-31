import http from "../http";

//要默认导出 api接口信息
export default {

    //后台的url api地址连接
    imgList: {
        name: "获取图片列表",
        url: "/api/upload",
        call: async function (params: any = {}) {
            return await http.get(this.url, params)
        }
    },
    upload: {
        name: "上传图片",
        url: "/api/upload",
        call: async function (params: any = {}) {
            return await http.post(this.url, params,)
        }
    }

}