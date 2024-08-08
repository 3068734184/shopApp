<!--  -->
<template>
    <div>
        <el-form :inline="true" :model="formData" class="demo-form-inline">
            <el-form-item label="用户名">
                <el-input v-model="formData.username" placeholder="请输入用户名字" clearable />
            </el-form-item>
            <el-form-item label="用户编号">
                <el-input v-model="formData.id" placeholder="请输入用户ID" clearable />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">查询</el-button>
            </el-form-item>
        </el-form>
        <!--列表数据-->
        <el-table :data="tableData" style="width: 100%">
            <el-table-column type="selection" width="55" />
            <el-table-column fixed prop="id" label="编号" width="90" />
            <el-table-column prop="username" label="用户名字" width="120" />
            <el-table-column prop="password" label="用户密码" width="120" />
            <el-table-column prop="birthday" label="出生日期" width="240" />
            <el-table-column prop="sex" label="性别" width="120" />
            <el-table-column prop="address" label="出生地" width="200" />
            <el-table-column prop="roleId" label="角色Id" width="80" />
            <el-table-column fixed="right" label="Operations" min-width="140">
                <template #default>
                    <el-button type="danger" size="small" @click="handleClick">
                        删除
                    </el-button>
                    <el-button type="primary" size="small">修改</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script setup lang='ts'>
import axios from 'axios'
import { onMounted, reactive, ref } from 'vue';

const formData = reactive({
    username: '',
    id: ''
})
//列表展示
const tableData = ref([])

const onSubmit = () => {
    console.log('submit!')
}


onMounted(() => {
    callplusUserList()
})

const callplusUserList = () => {
    axios({
        method: 'get',
        url: 'http://localhost:8080/api/select'
    }).then((res) => {
        console.log(res);
        tableData.value = res.data
    }).catch((error) => {
        console.log(error);
    }).finally(() => {
        console.log("永远执行的函数，无论是否报错都会执行");
    })
}




const handleClick = () => {
    console.log('click')
}
</script>

<style scoped>
.demo-form-inline {
    margin-top: 20px;
    margin-left: 20px;
}

.demo-form-inline .el-input {
    --el-input-width: 220px;
}

.demo-form-inline .el-select {
    --el-select-width: 220px;
}
</style>
