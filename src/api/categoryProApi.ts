import http from "../http";

export default {
    selectCategory:{
        name:"商品类别统计",
        url:"/api/catepropie",
        call:async function(params:any={}){
            return await http.get(this.url,params);
        }
    },

    selectCategoryCount:{
        name:"类目下的商品数量",
        url:"/api/categoryProCount",
        call:async function (params:any = {}) {
            return await http.get(this.url,params)
        }
    }
}