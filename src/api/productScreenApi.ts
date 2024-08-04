import http from '../http'

export default {
    selectScreen: {
        name: "分类库存",
        url: "/api/categoryStock",
        call: async function () {
            return await http.get(this.url)
        }
    },
    bannerImages: {
        name: "移动端轮播图",
        url: "/api/bannerImages",
        call:async function () {
            return await http.get(this.url)
        }
    }
}