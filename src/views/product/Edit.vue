<!-- productEdit -->
<template>
  <div>
    <el-form ref="formRef" style="max-width: 600px" :model="formData" :rules="rules" label-width="200px"
      class="demo-ruleForm" status-icon>
      <el-form-item label="产品名称" prop="name">
        <el-input v-model="formData.name" style="width: 240px;" clearable />
      </el-form-item>
      <el-form-item label="产品图片" prop="img">
        <Tuku v-model="formData.img"></Tuku>
      </el-form-item>
      <el-form-item label="产品排序" prop="seq">
        <el-input-number v-model="formData.seq" :min="1" :max="10" />
      </el-form-item>
      <el-form-item label="所属分类" prop="categroyId">
        <el-tree-select v-model="formData.categoryId" :data="categoryData" check-strictly :render-after-expand="false"
          style="width: 240px" />
      </el-form-item>
      <el-form-item label="产品价格" prop="price">
        <el-input-number :min="0" v-model="formData.price" controls-position="right" />
      </el-form-item>
      <el-form-item label="产品简介" prop="brief">
        <el-input v-model="formData.brief" style="width: 240px" autosize type="textarea" />
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
import { onMounted, reactive, ref } from 'vue'
import Tuku from '../../components/Tuku.vue'
import { categoryApi, productApi } from '../../api';
import { ElMessage } from 'element-plus';
import { useRoute, useRouter } from 'vue-router';


let route = useRoute()    //跳转传参
let router = useRouter()  //路由跳转

const formRef = ref()
const formData = reactive({
  id: 0,
  name: '',
  seq: 0,
  img: "",
  categoryId: 0,
  price: 0,
  brief: ""

})

const rules = reactive({
  name: [
    { required: true, message: '请输入类目的名称', trigger: 'blur' },
    { min: 2, max: 15, message: '类目名称不得小于2 大于15', trigger: 'blur' },
  ],
})

const categoryData = ref([
  {
    value: 0,
    label: '一级目录',
  }]
)

onMounted(() => {
  formData.id = Number(route.query.id)
  CallcategoryApi()
  CallproductApiSelectById()
})


const CallcategoryApi = () => {
  categoryApi.select.call().then((res: any) => {
    console.log("res==", res);
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
    // console.log(categoryData2);
    categoryData.value = categoryData2

  })
}

//根据id查找产品
const CallproductApiSelectById = () => {
  productApi.select.call({
    id: formData.id
  }).then((res: any) => {
    console.log("res==", res);
    formData.name = res.item[0].name
    formData.img = res.item[0].img
    formData.seq = res.item[0].seq
    formData.categoryId = res.item[0].categoryId
    formData.price = res.item[0].price
    formData.brief = res.item[0].brief
  })
}


//自己定义一个提交的方法 为了方便调试
const formsubmit = () => {
  productApi.update.call({
    id:formData.id,
    name: formData.name, img: formData.img,
    seq: formData.seq, categoryId: formData.categoryId,
    price: formData.price, brief: formData.brief
  }).then((res: any) => {
    console.log(res);

    ElMessage.success("修改成功")
    //跳转到列表页面
    router.push({ name: "product-list" })

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

<style scoped></style>
