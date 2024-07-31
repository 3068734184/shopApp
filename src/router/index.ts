import { createRouter, createWebHistory } from "vue-router"

const route = [
    
    {
    path: '/',
    name: 'home',
        component: () => import("../views/Home.vue"),
        meta: { title: "首页", show: true, icon:"House",useFrame:true}
    
    },
    {
        path: '/moveshow',
        name: 'moveshow',
        component: () => import("../views/MoveShow.vue"),
        meta: { title: "移动首页", show: false, icon: "House", useFrame: false }
    },
    {
        path: '/screen',
        name: 'screen',
        component: () => import("../views/screen/DatavScreen.vue"),
        meta: { title: "数据统计图", show: true, icon: "DataBoard", useFrame: true }

    },
    {
        path: '/category',
        name: 'category',
        component: () => import("../views/category/Index.vue"),
        meta: { title: "类目管理", show: true, icon: "Memo"},
        children: [
            {
                path: "/category/list",
                name: "category-list",
                component: () => import("../views/category/List.vue"),
                 meta: { title: "类目列表", show: true, icon: "List" },
            },
            {
                path: "/category/add",
                name: "category-add",
                component: () => import("../views/category/Add.vue"),
                meta: { title: "类目添加", show: true, icon: "Plus" },
            },
            {
                path: "/category/edit",
                name: "category-edit",
                component: () => import("../views/category/Edit.vue"),
                meta: { title: "类目修改", show: false, icon: "Edit" },
            }
        ]
    },



    {
        path: '/plususer',
        name: 'plususer',
        component: () => import("../views/userboot/PlusUser.vue")
    },
    {
        path: '/plusinsert',
        name: 'plusinsert',
        component: () => import("../views/userboot/PlusInsert.vue")
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes: route
})
//导出路由
export default router;