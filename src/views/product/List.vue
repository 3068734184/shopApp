<!-- productList -->
<template>
    <div style="margin-left: 10px;">
        <el-form :inline="true" :model="formData" class="demo-form-inline">
            <el-form-item label="产品编号">
                <el-input v-model="formData.id" placeholder="请输入产品ID" clearable />
            </el-form-item>
            <el-form-item label="产品名称">
                <el-input v-model="formData.name" placeholder="请输入name名字" clearable />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">查询</el-button>
            </el-form-item>
        </el-form>
        <!--展示列表-->
        <el-table :data="tableData" style="width: 100%">
            <el-table-column prop="id" label="编号"/>
            <el-table-column prop="name" label="产品名称"  />
            <el-table-column prop="img" label="产品图片" >
                <template #default="scope">
                    <img :src="scope.row.img" style="width:80px;">
                </template>
            </el-table-column>
            <el-table-column prop="categoryId" label="分类ID"></el-table-column>
            <el-table-column prop="status" label="状态码" />
            <el-table-column prop="seq" label="排序" />
            <el-table-column prop="price" label="价格">
                <template #default="scope">
                    {{ scope.row.price }}万
                </template>
            </el-table-column>
            <el-table-column prop="brief" label="简介"></el-table-column>
            <el-table-column fixed="right" label="操作">
                <template #default="scope">
                    <el-button type="danger" size="small" @click="OfflineClick(scope.row)">
                        下线
                    </el-button>
                    <el-button type="primary" size="small" @click="EditClick(scope.row)">修改</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination style="display: flex; justify-content: center; margin-top: 20px;" size="large" :page-size="pageData.pageSize" layout="prev, pager, next" :total="pageData.total"
            @current-change="currentChange" />
    </div>
</template>

<script setup lang='ts'>
import { productApi } from '../../api';
import { reactive, onMounted, ref } from 'vue'
import { useRouter } from 'vue-router';
import { ElMessage, ElMessageBox } from 'element-plus';
const router = useRouter()
const formData = reactive({
    id: '',
    name: '',
    ids: []

})
const pageData = reactive({
    total: 10,
    pageSize: 3,
    pageNum: 1
})

const tableData = ref()

onMounted(() => {
    callProductApi()
})

//定义函数进行数据回调
const callProductApi = () => {
    let id = formData.id === '' ? undefined : formData.id
    let name = formData.name === '' ? undefined : formData.name

    // console.log("id==",id);

    productApi.select.call({
        id: id, name: name,
        pageNum: pageData.pageNum,
        pageSize: pageData.pageSize
    }).then((res: any) => {
        // console.log("callProductApi==",res);
        // console.log(res.data);
        tableData.value = res.item;
        pageData.total = res.total

    })
}


const onSubmit = () => {
    console.log('submit!')
    callProductApi()
}
const currentChange = (num: number) => {
    // console.log(`current page: ${num}`)
    pageData.pageNum = num
    // console.log("pageData==", pageData);
    callProductApi()
}

//修改
const EditClick = (row: any) => {
    router.push({ name: 'product-edit', query: { id: row.id } })
}
//下线
const OfflineClick = (row: any) => {
    ElMessageBox.confirm(
        '您确定要对名称为 ' + row.name + ' 的产品进行下线吗？',
        '警告',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(() => {
            productApi.delete.call({ id: row.id }).then((res: any) => {
                console.log(res);

                ElMessage({
                    type: 'success',
                    message: '下线成功',
                })
                //回调函数刷新当前页面
                callProductApi()
            })

        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '取消下线',
            })
        })
}
</script>

<style scoped>
.demo-form-inline {
    margin-top: 20px;
}

.demo-form-inline .el-input {
    --el-input-width: 220px;
}

.demo-form-inline .el-select {
    --el-select-width: 220px;
}
</style>
