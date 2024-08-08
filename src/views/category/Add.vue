<!--  -->
<template>
  <el-form ref="formRef" style="max-width: 600px" :model="formData" :rules="rules" label-width="200px"
    class="demo-ruleForm" status-icon>
    <el-form-item label="类目名称" prop="name">
      <el-input v-model="formData.name" />
    </el-form-item>
    <el-form-item label="类目图片" prop="img">
      <Tuku v-model="formData.img"></Tuku>
    </el-form-item>
    <el-form-item label="类目排序" prop="seq">
      <el-input-number v-model="formData.seq" :min="1" :max="10" />
    </el-form-item>
    <el-form-item label="父类ID" prop="parentId">
      <el-tree-select v-model="formData.parentId" :data="categoryData" check-strictly :render-after-expand="false"
        style="width: 240px" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm(formRef)">
        提交
      </el-button>
      <el-button @click="resetForm(formRef)">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup lang='ts'>

import { onMounted, reactive, ref } from 'vue'
import Tuku from '../../components/Tuku.vue'
import { categoryApi } from '../../api';
import { ElMessage } from 'element-plus';
import { useRouter } from 'vue-router';

// let route = useRoute()    //跳转传参
let router = useRouter()  //路由跳转

const formRef = ref()
const formData = reactive({
  name: '',
  seq: 0,
  img: "",
  parentId: 0
  
})

const rules = reactive({
  name: [
    { required: true, message: '请输入类目的名称', trigger: 'blur' },
    { min: 2, max: 5, message: '类目名称不得小于2 大于5', trigger: 'blur' },
  ],
})

const categoryData = ref([
  {
    value: 0,
    label: '一级目录',
  }]
)

onMounted(() => {
  CallcategoryApi()
})


const CallcategoryApi = () => {
  categoryApi.select.call().then((res: any) => {
    console.log("res==",res);
    //获取，名称和id值  对应的是列表里面的value和label
    res.item.forEach((item: any) => {
      item.value = item.id
      item.label = item.name
    });
    //高阶函数
    const categoryData2 = res.item.reduce((hezhi: any, currentValue: any) => {

      //console.log(currentValue);
      currentValue.children = res.item.filter((item: any) => {
        return item.parentId === currentValue.id
      })
      if (currentValue.parentId === 0) {
        hezhi.push(currentValue)
      }
      return hezhi
    }, [])
    console.log(categoryData2);
    categoryData.value = categoryData2

  })
}


//自己定义一个提交的方法 为了方便调试
const formsubmit = () => {
  // CategoryApi.insert.call(formData).then((res: any) => { 
  //     console.log(res);  
  // })
  categoryApi.insert.call({
    name: formData.name, img: formData.img,
    seq: formData.seq, parentId: formData.parentId
  }).then((res: any) => {
    console.log(res);

    ElMessage.success("添加成功")
    //跳转到列表页面
    router.push({ name: "category-list" })

  })
}

const submitForm = async (formEl: any) => {
  if (!formEl) return
  await formEl.validate((valid: any, fields: any) => {
    if (valid) {
      console.log('submit!')
      formsubmit()
    } else {
      console.log('error submit!', fields)
    }
  })
}

const resetForm = (formEl: any) => {
  if (!formEl) return
  formEl.resetFields()
}
</script>

<style scoped>
.demo-ruleForm {
  margin-top: 20px;
  margin-left: 20px;
}
</style>