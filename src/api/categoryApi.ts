import http from "../http";

//要默认导出 api接口信息
export default {

    //后台的url api地址连接
    select: {
        name: "类目列表",
        url: "/api/category",
        /* async 负责声明一个异步方法 返回一个promise对象 then(回调函数)
          await 等待异步方法执行完成
        params: any = {} 如果传递的值是params 参数就是any类型 如果传的是data {编写存储值}
        可以执行 post请求和get d put
        */
        call: async function (params: any = {}) {
            return await http.get(this.url, params)
        }
    },
    insert: {
        name: "类目添加",
        url: "/api/category",
        call: async function (params: any = {}) {
            return await http.post(this.url, params)
        }
    },
    update: {
        name: "类目修改",
        url: "/api/category",
        call: async function (params: any = {}) {
            return await http.put(this.url, params)
        }
    },
    delete: {
        name: "类目删除",
        url: "/api/category",
        call: async function (params: any = {}) {
            return await http.delete(this.url, params)
        }
    },
    MSelect:{
        name:"移动端分类",
        url:"/api/category/MSelect",
        call:async function () {
            return await http.get(this.url)
        }
    }


}