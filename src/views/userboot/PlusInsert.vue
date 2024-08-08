<!--  -->
<template>
    <div>
        <el-form ref="formRef" style="max-width: 500px" :model="formData" :rules="rules" label-width="auto"
            class="demo-ruleForm" status-icon>
            <el-form-item label="用户名" prop="username">
                <el-input v-model="formData.username" />
            </el-form-item>

            <el-form-item label="用户生日" required>
                <el-col :span="11">
                    <el-form-item prop="birthday">
                        <el-date-picker v-model="formData.birthday" type="date" aria-label="Pick a date"
                            placeholder="Pick a date" style="width: 100%" />
                    </el-form-item>
                </el-col>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm(formRef)">
                    提交
                </el-button>
                <el-button @click="resetForm(formRef)">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script setup lang='ts'>
import { reactive, ref } from 'vue';

const formRef = ref()
const formData = reactive({
    username: '',
    password: '',
    birthday: '',
    sex: '',
    address: '',
    roleId: 0
})

const rules = reactive({
    username: [
        { required: true, message: 'Please input Activity name', trigger: 'blur' },
        { min: 3, max: 15, message: 'Length should be 3 to 5', trigger: 'blur' },
    ]
})

const submitForm = async (formEl:any) => {
    if (!formEl) return
    await formEl.validate((valid:any, fields:any) => {
        if (valid) {
            console.log('submit!')
        } else {
            console.log('error submit!', fields)
        }
    })
}

const resetForm = (formEl:any) => {
    if (!formEl) return
    formEl.resetFields()
}
</script>

<style scoped></style>
